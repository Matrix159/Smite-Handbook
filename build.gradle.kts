@file:Suppress("DSL_SCOPE_VIOLATION")
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  extra.apply {
    set("accompanist_version", "0.26.3-beta")
    set("coil_version", "2.2.1")
    set("compose_version", "1.3.1")
    set("datastore_version", "1.0.0")
    set("androidXHiltVersion", "1.0.0")
    //set("junit_version", "4.13.2")
    //set("kotlinx_coroutines_test", "1.6.4")
    set("lifecycle_version", "2.6.0-alpha03")
    set("lottie_version", "5.2.0")
    set("mockk_version", "1.12.8")
    set("room_version", "2.4.3")
    set("workVersion", "2.7.1")
  }

  dependencies {
    //classpath("org.jetbrains.kotlin:kotlin-serialization:${kotlin_version}")
    //classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
  }
}

plugins {
  alias(libs.plugins.androidApplication).apply(false)
  alias(libs.plugins.androidLibrary).apply(false)
  alias(libs.plugins.kotlinMultiplatform).apply(false)
  alias(libs.plugins.kotlinAndroid).apply(false)
  alias(libs.plugins.kotlinSerialization).apply(false)
  alias(libs.plugins.sqlDelight).apply(false)
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
