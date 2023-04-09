@file:Suppress("DSL_SCOPE_VIOLATION")
import java.io.FileInputStream
import java.util.*

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
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
    implementation(project(":shared"))
    implementation(libs.androidCoreKtx)
    implementation(libs.workManager)
    implementation(libs.timber)

    // debugImplementation because LeakCanary should only run in debug builds.
    debugImplementation(libs.leakCanary)
}
