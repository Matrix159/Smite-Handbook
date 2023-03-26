plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("com.android.library")
  id("com.squareup.sqldelight")
  //id("com.google.devtools.ksp")
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

  val ktorVersion = "2.1.2"
  val coroutinesVersion = "1.6.4"
  val sqlDelightVersion = "1.5.5"
  val koinVersion = "3.2.2"
  val koinAndroidVersion = "3.3.0"
  val kotlinxCoroutinesTest = "1.6.4"
  val jUnitVersion = "4.13.2"
  sourceSets {
    val commonMain by getting {
      dependencies {       implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
        implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
        implementation("io.ktor:ktor-client-logging:$ktorVersion")
        implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
        implementation("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")
        // timber for logging
        //implementation("com.jakewharton.timber:timber:5.0.1")
        // Logging, using this until timber supports KMP
        implementation("co.touchlab:kermit:1.2.2")

        // Settings
        implementation("com.russhwolf:multiplatform-settings:1.0.0-RC")
        //implementation("com.russhwolf:multiplatform-settings-datastore:1.0.0-RC")

        // KOIN DI
        // Koin Core features
        api("io.insert-koin:koin-core:$koinVersion")
        // Koin for JUnit 4
        //implementation("io.insert-koin:koin-test-junit4:$koinVersion")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
        //implementation("junit:junit:$jUnitVersion")
        // Koin Test features
        implementation("io.insert-koin:koin-test:$koinVersion")
        //For runBlockingTest, CoroutineDispatcher etc.
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinxCoroutinesTest")
      }
    }
    val androidMain by getting {
      dependencies {
        implementation("io.ktor:ktor-client-android:$ktorVersion")
        implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")

        // Koin main features for Android
        api("io.insert-koin:koin-android:$koinAndroidVersion")
        // Koin for Jetpack Compose
        api("io.insert-koin:koin-androidx-compose:$koinAndroidVersion")
        // Jetpack WorkManager
        api("io.insert-koin:koin-androidx-workmanager:$koinAndroidVersion")
        // Navigation Graph
        api("io.insert-koin:koin-androidx-navigation:$koinAndroidVersion")
        // Old data module deps below
        // hilt
        //implementation("com.google.dagger:hilt-android:${rootProject.extra.get("hilt_version")}")
//        configurations["kapt"].dependencies.add(
//          project.dependencies.create(
//            "com.google.dagger:hilt-android-compiler:${
//              rootProject.extra.get(
//                "hilt_version"
//              )
//            }"
//          )
//        )
        // two below are for working with WorkManager in hilt
//        implementation("androidx.hilt:hilt-work:${rootProject.extra.get("androidx_hilt_version")}")
//        configurations["kapt"].dependencies.add(
//          project.dependencies.create(
//            "androidx.hilt:hilt-compiler:${
//              rootProject.extra.get(
//                "androidx_hilt_version"
//              )
//            }"
//          )
//        )


        // data store
        implementation("androidx.datastore:datastore-preferences:${rootProject.extra.get("datastore_version")}")

//
//                testImplementation "junit:junit:$junit_version"
//                testImplementation "io.mockk:mockk:$mockk_version"
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
        implementation("io.ktor:ktor-client-darwin:$ktorVersion")
        implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
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

dependencies {
//  add("kspAndroid",  org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
//    "androidx.room",
//    "room-compiler",
//    "2.4.3"
//  ))
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

