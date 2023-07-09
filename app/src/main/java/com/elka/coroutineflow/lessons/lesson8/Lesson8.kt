package com.elka.coroutineflow.lessons.lesson8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

val coroutineContext = CoroutineScope(Dispatchers.IO)

suspend fun main() {
  val flow = getFlow()  // без подписки код не выполняется

  // при каждой подписки создается свой flow
  val job1 = flow.launchIn(coroutineContext)
  val job2 = coroutineContext.launch {
    flow.first().let { println("First: $it. And flow close") }    // прекращает выполнение после получения 1-ого числа
  }

  job1.join()
  job2.join()
}

fun getFlow(): Flow<Int> = flow {
  repeat(100) {
    println("Emited: $it")
    emit(it)
    delay(1000)
  }
}