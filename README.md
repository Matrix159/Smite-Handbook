# Smite Handbook

## 📚 Overview

Smite Handbook is an application to give Smite existing smite players or newcomers to the game the 
information they need to learn more about the game and other useful features. 

Smite Handbook uses an API provided by Hi-Rez Studios but it is not affiliated with Hi-Rez Studios.

It is available as a beta application in the Play Store [here](https://play.google.com/store/apps/details?id=com.matrix.materializedsmite).

## 📋 Features

* View all the gods, their abilities, lore, and skins with the ability to filter down and search for a specific god.
* View all the items, their stats, what item tree they belong to, and the ability to filter down and search for a specific item.
* Ability to create a list of builds for gods and customize the items for the builds.

## 🏛️ Architecture

Smite Handbook follows [Google's official architecture guidance](https://developer.android.com/topic/architecture) and utilizes an offline-first approach to 
present a faster experience with less data used.

## 🔨 Technologies Used

* [Coil](https://coil-kt.github.io/coil/) for image loading.
* [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for asynchronous operations.
* [Datastore](https://developer.android.com/topic/libraries/architecture/datastore) for saving data related to offline sync.
* [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) for crash reporting and exception logging.
* [Jetpack Compose](https://developer.android.com/jetpack/compose) for views.
* [Kermit](https://github.com/touchlab/Kermit) for general multiplatform logging.
* [Koin](https://insert-koin.io/) for multiplatform dependency injection.
* [Ktor Client](https://ktor.io/) for multiplatform network requests.
* [Lottie](https://github.com/airbnb/lottie) for custom animations.
* [SQLDelight](https://github.com/cashapp/sqldelight) for a multiplatform SQL database solution for offline-caching and storage.
* [Timber](https://github.com/JakeWharton/timber) an Android specific wrapper around logging.
* [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) to run scheduled background tasks to sync data to offline-storage.

## 🌄 Examples

<p float="left">
  <img src='https://user-images.githubusercontent.com/5334090/164605568-e3abd4eb-e23f-4c5e-a90b-7df1979cb798.png' width='300'>
  <span>&nbsp;</span>
  <img src='https://user-images.githubusercontent.com/5334090/164605576-c45b3628-15a3-45c6-b0c0-0ec467fcaa6c.png' width='300'>
  <img src='https://user-images.githubusercontent.com/5334090/187100396-6c1fc85c-6ee8-4066-beb6-7e6df79a4d69.png' width='300'>
  <img src='https://user-images.githubusercontent.com/5334090/187100401-61baad2c-bda3-422a-87dc-862ff2db9ffa.png' width='300'>
</p>
