package com.elka.coroutineflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

suspend fun main() {
  getFlowByBuilderFlow().filter { it.isPrimary() }
    .filter { it > 50 }
    .map { "Number: $it" }
    .collect { println(it) }
}

fun getFlowByBuilderFlow(): Flow<Int> {
  val numbers = listOf(4, 3, 7, 8, 11, 32, 43, 47, 84, 116, 53, 59)
  return flow {
    numbers.forEach { emit(it) }
  }
}

private suspend fun Int.isPrimary(): Boolean {
  if (this <= 1) return false
  for (i in 2..this / 2) {
    delay(50)
    if (this % i == 0) return false
  }
  return true
}
