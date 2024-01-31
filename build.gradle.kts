@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
  alias(libs.plugins.androidApplication).apply(false)
  alias(libs.plugins.androidLibrary).apply(false)
  alias(libs.plugins.kotlinMultiplatform).apply(false)
  alias(libs.plugins.kotlinAndroid).apply(false)
  alias(libs.plugins.kotlinSerialization).apply(false)
  alias(libs.plugins.sqlDelight).apply(false)
  alias(libs.plugins.googleServices).apply(false)
  alias(libs.plugins.firebaseCrashlytics).apply(false)
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
