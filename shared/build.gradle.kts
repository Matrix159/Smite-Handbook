plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("com.android.library")
  kotlin("kapt")
}

kotlin {
  android {
//        plugins {
//            id("com.android.library")
//            id("org.jetbrains.kotlin.android")
//            id("kotlinx-serialization")
//            //kotlin("kapt") version "1.7.21"
//        }
  }

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

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
        implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
        implementation("io.ktor:ktor-client-logging:$ktorVersion")
        // timber for logging
        //implementation("com.jakewharton.timber:timber:5.0.1")
        // Logging, using this until timber supports KMP
        implementation("co.touchlab:kermit:1.2.2")

        // Settings
        implementation("com.russhwolf:multiplatform-settings:1.0.0-RC")
        //implementation("com.russhwolf:multiplatform-settings-datastore:1.0.0-RC")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
        //For runBlockingTest, CoroutineDispatcher etc.
        //testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinx_coroutines_test")
      }
    }
    val androidMain by getting {
      dependencies {
        implementation("io.ktor:ktor-client-android:$ktorVersion")

        // Old data module deps below
        // hilt
        implementation("com.google.dagger:hilt-android:${rootProject.extra.get("hilt_version")}")
        configurations["kapt"].dependencies.add(
          project.dependencies.create(
            "com.google.dagger:hilt-android-compiler:${
              rootProject.extra.get(
                "hilt_version"
              )
            }"
          )
        )
        // two below are for working with WorkManager in hilt
        implementation("androidx.hilt:hilt-work:${rootProject.extra.get("androidx_hilt_version")}")
        configurations["kapt"].dependencies.add(
          project.dependencies.create(
            "androidx.hilt:hilt-compiler:${
              rootProject.extra.get(
                "androidx_hilt_version"
              )
            }"
          )
        )


//                // timber for logging
//                implementation "com.jakewharton.timber:timber:5.0.1"
//
//                // work manager
//                // Kotlin + coroutines
        implementation("androidx.work:work-runtime-ktx:${rootProject.extra.get("work_version")}")
//                // optional - Test helpers
//                androidTestImplementation "androidx.work:work-testing:$work_version"
//
        // data store
        implementation("androidx.datastore:datastore-preferences:${rootProject.extra.get("datastore_version")}")
//
//                // app startup
//                implementation "androidx.startup:startup-runtime:1.1.1"
//
//
        // room db
        implementation("androidx.room:room-runtime:${rootProject.extra.get("room_version")}")
        configurations["annotationProcessor"].dependencies.add(
          project.dependencies.create(
            "androidx.room:room-compiler:${
              rootProject.extra.get(
                "room_version"
              )
            }"
          )
        )
        //annotationProcessor("androidx.room:room-compiler:${rootProject.extra.get("room_version")}")
        implementation("androidx.room:room-ktx:${rootProject.extra.get("room_version")}")
        // To use Kotlin annotation processing tool (kapt)
        //configurations["kapt"].dependencies.add(project.dependencies.create("androidx.room:room-compiler:${rootProject.extra.get("room_version")}"))
        //kapt("androidx.room:room-compiler:${rootProject.extra.get("room_version")}")
        // optional - Test helpers
        //testImplementation("androidx.room:room-testing:${rootProject.extra.get("room_version")}")
//
//                testImplementation "junit:junit:$junit_version"
//                testImplementation "io.mockk:mockk:$mockk_version"
      }
    }
    val androidTest by getting
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
  defaultConfig {
    minSdk = 26
    targetSdk = 33

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
  binaries.all {
    binaryOptions["memoryModel"] = "experimental"
  }
}