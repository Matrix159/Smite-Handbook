package com.matrix.data.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import timber.log.Timber

private const val TIME_OUT = 120 * 1000 // 120 seconds as milliseconds

@ExperimentalSerializationApi
val ktorHttpClient = HttpClient(Android) {
  engine {
    connectTimeout = TIME_OUT
    socketTimeout = TIME_OUT
  }

  install(ContentNegotiation) {
    json(Json {
      ignoreUnknownKeys = true
      explicitNulls = false
    })
  }

  install(Logging) {
    logger = object : Logger {
      override fun log(message: String) {
        Timber.v("Logger Ktor =>", message)
      }

    }
    level = LogLevel.INFO
  }

  install(ResponseObserver) {
    onResponse { response ->
      Timber.d("HTTP status:", "${response.status.value}")
    }
  }

  install(DefaultRequest) {
    header(HttpHeaders.ContentType, ContentType.Application.Json)
  }

  install(HttpCache)
}