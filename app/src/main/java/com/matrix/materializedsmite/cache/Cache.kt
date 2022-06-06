package com.matrix.materializedsmite.cache

import android.content.SharedPreferences
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

interface Cache<Key : Any, Value : Any> {
  suspend fun getAsync(key: Key): Value?
  suspend fun setAsync(key: Key, value: Value): Unit
}

//fun transform(value: Value): String {
//  return Js.stringify(Value::class::serializer, value)
//}fun inverseTransform(mappedValue: String): Value {
//  return JSON.parse(Value::class::serializer, mappedValue)
//}

//fun <Key: Any, Value: Any> compose(fallbackCache: Cache<Key, Value>): Cache<Key, Value> {
//  return object : Cache<Key, Value> {
//    override fun getAsync(key: Key): Deferred<Value?> {
//      return GlobalScope.async {
//        this@Cache.getAsync(key).await() ?: let {
//          fallbackCache.getAsync(key).await()?.apply {
//            this@Cache.set(key, this).await()
//          }
//        }
//      }
//    }
//
//    override fun setAsync(key: Key, value: Value): Deferred<Unit> {
//      return GlobalScope.async {
//        listOf(this@Cache.set(key, value), b.set(key, value)).forEach { it.await() }
//      }
//    }
//  }
//}