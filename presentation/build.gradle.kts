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
        kotlinCompilerExtensionVersion = "1.3.1"
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    // TODO: Migrate to M3 fully when we can
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    // Material3 in Compose
    implementation("androidx.compose.material3:material3:1.1.0-alpha02")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    //implementation "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha04"

    // accompanist
    implementation("com.google.accompanist:accompanist-placeholder-material:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-navigation-animation:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-flowlayout:$accompanistVersion")

    // hilt
//    implementation("com.google.dagger:hilt-android:$hilt_version")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
//    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    // ktor serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    // image loading - coil
    implementation("io.coil-kt:coil-compose:$coilVersion")

    //lottie
    implementation("com.airbnb.android:lottie-compose:$lottieVersion")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // timber
    implementation("com.jakewharton.timber:timber:5.0.1")
    // base test dependencies
    testImplementation(project(":shared"))
    testImplementation(kotlin("test"))
//    testImplementation("junit:junit:4.13.2")
//    testImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    testImplementation("org.robolectric:robolectric:4.9")
//    testImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    //For runBlockingTest, CoroutineDispatcher etc.
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinxCoroutinesTest")
    androidTestImplementation(project(":shared"))
    androidTestImplementation(kotlin("test"))
    //androidTestImplementation("androidx.test.ext:junit:1.1.5")
    //androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    // Needed for createAndroidComposeRule, but not createComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
}