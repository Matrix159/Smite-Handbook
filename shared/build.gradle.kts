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
  val sqlDelightVersion = "1.5.3"
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
        implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
        implementation("io.ktor:ktor-client-logging:$ktorVersion")
        implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
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
        implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")

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


//                // timber for logging
//                implementation "com.jakewharton.timber:timber:5.0.1"
//
//                // work manager
//                // Kotlin + coroutines
        //implementation("androidx.work:work-runtime-ktx:${rootProject.extra.get("work_version")}")
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
//        configurations.getByName("annotationProcessor").dependencies.add(
//          org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
//            "androidx.room",
//            "room-compiler",
//            "2.4.3"
//          )
//        )
        //add("kspAndroid", project(":test-processor"))
//        configurations.getByName("ksp").dependencies.add(
//          org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
//            "androidx.room",
//            "room-compiler",
//            "2.4.3"
//          )
//        )
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

sqldelight {
  database("SmiteHandbookDatabase") {
    packageName = "com.matrix.sqldelight"
  }
}

kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
  binaries.all {
    binaryOptions["memoryModel"] = "experimental"
  }
}