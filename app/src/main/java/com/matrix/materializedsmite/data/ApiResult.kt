package com.matrix.materializedsmite.data

/**
 * A generic class that holds a value or an exception
 */
sealed class ApiResult<out R> {
  data class Success<out T>(val data: T) : ApiResult<T>()
  data class Error(val exception: Exception) : ApiResult<Nothing>()
}

fun <T> ApiResult<T>.successOr(fallback: T): T {
  return (this as? ApiResult.Success<T>)?.data ?: fallback
}