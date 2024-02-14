plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("com.android.library")
  id("com.squareup.sqldelight")
}

kotlin {
  androidTarget()

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "shared"
    }
  }

  sourceSets {
    commonMain.dependencies {
      implementation(libs.coroutinesCore)
      implementation(libs.ktorClientCore)
      implementation(libs.ktorClientContentNegotiation)
      implementation(libs.ktorSerializationJson)
      implementation(libs.ktorClientLogging)
      implementation(libs.sqlDelightRuntime)
      implementation(libs.sqlDelightCoroutines)
      // Logging, using this until timber supports KMP
      implementation(libs.kermit)

      // Koin DI
      api(libs.koinCore)
    }
    commonTest.dependencies {
      implementation(kotlin("test"))
      //implementation("junit:junit:$jUnitVersion")
      // Koin Test features
      implementation(libs.koinTest)
      //For runBlockingTest, CoroutineDispatcher etc.
      implementation(libs.coroutinesTest)
    }
    androidMain.dependencies {
      implementation(libs.ktorClientAndroid)
      implementation(libs.sqlDelightAndroid)

      // Koin main features for Android
      api(libs.koinAndroid)
      // Koin for Jetpack Compose
      api(libs.koinAndroidXNavigation)
      // Jetpack WorkManager
      api(libs.koinWorkManager)
      // Navigation Graph
      api(libs.koinAndroidXNavigation)

      // data store
      implementation(libs.datastorePreferences)

    }
    iosMain.dependencies {
      implementation(libs.ktorClientDarwin)
      implementation(libs.sqlDelightNativeDriver)
    }
  }

  jvmToolchain(17)
}

android {
  namespace = "com.matrix.shared"
  compileSdk = 34
  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  defaultConfig {
    minSdk = 26
    consumerProguardFiles("consumer-rules.pro")

    javaCompileOptions {
      annotationProcessorOptions {
        arguments += "room.schemaLocation" to "$projectDir/schemas"
      }
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  lint {
    abortOnError=false
  }
}

sqldelight {
  database("SmiteHandbookDatabase") {
    packageName = "com.matrix"
    dialect = "sqlite:3.25"

    // The directory where to store '.db' schema files relative to the root
    // of the project. These files are used to verify that migrations yield
    // a database with the latest schema. Defaults to null so the verification
    // tasks will not be created.
    schemaOutputDirectory = file("src/commonMain/sqldelight/databases")
    verifyMigrations = true
  }
}

kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
  binaries.all {
    binaryOptions["memoryModel"] = "experimental"
  }
}

