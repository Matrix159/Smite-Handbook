
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    kotlin("kapt")
    //id("com.google.dagger.hilt.android")
}

android {
    signingConfigs {
        create("release") {
            // Load keystore
            val keystorePropertiesFile = rootProject.file("keystore.properties");
            val keystoreProperties = Properties()
            if (keystorePropertiesFile.exists()) {
                keystoreProperties.load(FileInputStream(keystorePropertiesFile))
                storeFile = file(keystoreProperties.getProperty("storeFile"))
                storePassword = keystoreProperties.getProperty("storePassword")
                keyAlias = keystoreProperties.getProperty("keyAlias")
                keyPassword = keystoreProperties.getProperty("keyPassword")
            }
        }
    }
    compileSdk = 33

    defaultConfig {
        applicationId = "com.matrix.materializedsmite"
        minSdk = 26
        targetSdk = 33
        versionCode = 9
        versionName = "0.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.matrix.materializedsmite"

    lint {
        abortOnError=false
    }
}

dependencies {
    implementation(project(":presentation"))
    //implementation(project(":data"))
    implementation(project(":shared"))

    implementation("androidx.core:core-ktx:1.9.0")

    // hilt (base)
    implementation("com.google.dagger:hilt-android:${rootProject.extra.get("hilt_version")}")
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.extra.get("hilt_version")}")
    // hilt with androidx
    implementation("androidx.hilt:hilt-navigation-compose:${rootProject.extra.get("androidx_hilt_version")}")
    implementation("androidx.hilt:hilt-work:${rootProject.extra.get("androidx_hilt_version")}")
    implementation("androidx.work:work-runtime-ktx:${rootProject.extra.get("work_version")}")
    kapt("androidx.hilt:hilt-compiler:${rootProject.extra.get("androidx_hilt_version")}")

    // timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // debugImplementation because LeakCanary should only run in debug builds.
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")

    // base test dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}