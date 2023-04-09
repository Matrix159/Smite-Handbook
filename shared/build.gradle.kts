plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("com.android.library")
  id("com.squareup.sqldelight")
}

kotlin {
  android()

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
    val commonMain by getting {
      dependencies {
        implementation(libs.coroutinesCore)
        implementation(libs.ktorClientCore)
        implementation(libs.ktorClientContentNegotiation)
        implementation(libs.ktorSerializationJson)
        implementation(libs.ktorClientLogging)
        implementation(libs.sqlDelightRuntime)
        implementation(libs.sqlDelightCoroutines)
        // timber for logging
        //implementation("com.jakewharton.timber:timber:5.0.1")
        // Logging, using this until timber supports KMP
        implementation(libs.kermit)

        // Settings
        implementation(libs.multiplatformSettings)
        //implementation("com.russhwolf:multiplatform-settings-datastore:1.0.0-RC")

        // Koin DI
        api(libs.koinCore)
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
        //implementation("junit:junit:$jUnitVersion")
        // Koin Test features
        implementation(libs.koinTest)
        //For runBlockingTest, CoroutineDispatcher etc.
        implementation(libs.coroutinesTest)
      }
    }
    val androidMain by getting {
      dependencies {
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
    }
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)

      dependencies {
        implementation(libs.ktorClientDarwin)
        implementation(libs.sqlDelightNativeDriver)
      }
    }
    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }
  }
}

android {
  namespace = "com.matrix.shared"
  compileSdk = 33
  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  defaultConfig {
    minSdk = 26
    targetSdk = 33

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
  }
}

kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
  binaries.all {
    binaryOptions["memoryModel"] = "experimental"
  }
}

