plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    //id "dagger.hilt.android.plugin"
    id("kotlin-parcelize")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =  true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    namespace = "com.matrix.presentation"

    lint {
        abortOnError=false
    }

    testOptions {
       unitTests.isIncludeAndroidResources = true
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(":shared"))

    implementation(libs.androidCoreKtx)
    implementation(libs.composeUi)
    implementation(libs.composeUiToolingPreview)
    // TODO: Migrate to M3 fully when we can
    implementation(libs.composeMaterial)
    implementation(libs.composeMaterialIcons)
    // Material3 in Compose
    implementation(libs.composeMaterial3)
    // ViewModel
    implementation(libs.lifecycleViewmodelKtx)
    implementation(libs.lifecycleRuntimeCompose)
    implementation(libs.activityCompose)
    implementation(libs.navigationCompose)

    // accompanist
    implementation(libs.accompanistFlowLayout)
    implementation(libs.accompanistPlaceholderMaterial)

    // image loading - coil
    implementation(libs.coilCompose)

    //lottie
    implementation(libs.lottieCompose)

    // coroutines
    implementation(libs.coroutinesAndroid)

    // timber
    implementation(libs.timber)

    // koin
    implementation(libs.koinCompose)

    // base test dependencies
    testImplementation(project(":shared"))
    testImplementation(kotlin("test"))

    //For runBlockingTest, CoroutineDispatcher etc.
    testImplementation(libs.coroutinesTest)
    androidTestImplementation(project(":shared"))
    androidTestImplementation(kotlin("test"))
    androidTestImplementation(libs.composeUiTestJunit4)
    // Needed for createAndroidComposeRule, but not createComposeRule:
    debugImplementation(libs.composeUiTestManifest)
    debugImplementation(libs.composeUiTooling)
}
