// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  val kotlin_version = "1.7.10"
  val hilt_version = "2.44"
  val sqlDelightVersion = "1.5.3"
  extra.apply {
    set("accompanist_version", "0.26.3-beta")
    set("coil_version", "2.2.1")
    set("compose_version", "1.3.1")
    set("datastore_version", "1.0.0")
    set("hilt_version", hilt_version)
    set("androidx_hilt_version", "1.0.0")
    //set("junit_version", "4.13.2")
    set("kotlin_version", kotlin_version)
    //set("kotlinx_coroutines_test", "1.6.4")
    set("lifecycle_version", "2.6.0-alpha03")
    set("lottie_version", "5.2.0")
    set("mockk_version", "1.12.8")
    set("room_version", "2.4.3")
    set("work_version", "2.7.1")
  }

  dependencies {
    //classpath("com.android.tools.build:gradle:8.0.0-alpha08")
    classpath("org.jetbrains.kotlin:kotlin-serialization:${kotlin_version}")
    classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
    //classpath("com.google.dagger:hilt-android-gradle-plugin:${hilt_version}")
  }
}

plugins {
  id("com.android.application").version("7.3.1").apply(false)
  id("com.android.library").version("7.3.1").apply(false)
  kotlin("android").version("1.7.10").apply(false)
  kotlin("multiplatform").version("1.7.10").apply(false)
  //id("com.google.dagger.hilt.android") version "2.44" apply false
  //id("com.google.devtools.ksp") version " 1.7.10-1.0.6" apply false
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}