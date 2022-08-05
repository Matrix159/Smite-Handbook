package com.matrix.data.impl

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

private const val TIME_OUT = 60_000

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
        Log.v("Logger Ktor =>", message)
      }

    }
    level = LogLevel.INFO
  }

  install(ResponseObserver) {
    onResponse { response ->
      Log.d("HTTP status:", "${response.status.value}")
      Log.d("HTTP data:", response.body())
    }
  }

  install(DefaultRequest) {
    header(HttpHeaders.ContentType, ContentType.Application.Json)
  }

  install(HttpCache)
}