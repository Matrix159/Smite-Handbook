package com.matrix.shared

import com.matrix.SmiteHandbookDatabase

expect class KmmAppContext private constructor() {
  fun getDatabase(): SmiteHandbookDatabase
}