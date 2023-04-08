plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    //id "dagger.hilt.android.plugin"
    id("kotlin-parcelize")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 26
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled =  true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
//        // This is needed until we can upgrade to kotlin 1.6.20
//        // https://issuetracker.google.com/issues/217593040?pli=1
//        freeCompilerArgs += [
//                "-Xjvm-default=all",
//        ]
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packagingOptions {
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

val accompanistVersion = "0.26.3-beta"
val coilVersion = "2.2.1"
val composeVersion = "1.3.1"
val lifecycleVersion = "2.6.0-alpha03"
val lottieVersion = "5.2.0"
val kotlinxCoroutinesTest = "1.6.4"

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
    implementation(libs.accompanistNavigationAnimation)
    implementation(libs.accompanistPlaceholderMaterial)
    implementation(libs.accompanistSystemUiController)

    // ktor serialization
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    // image loading - coil
    implementation(libs.coilCompose)

    //lottie
    implementation(libs.lottieCompose)

    // coroutines
    implementation(libs.coroutinesAndroid)

    // timber
    implementation(libs.timber)
    // base test dependencies
    testImplementation(project(":shared"))
    testImplementation(kotlin("test"))

    //For runBlockingTest, CoroutineDispatcher etc.
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinxCoroutinesTest")
    androidTestImplementation(project(":shared"))
    androidTestImplementation(kotlin("test"))
    //androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    // Needed for createAndroidComposeRule, but not createComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
}
