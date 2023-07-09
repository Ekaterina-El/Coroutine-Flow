package com.elka.coroutineflow.crypto_app

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

object CryptoRepository {

  private val currencyNames = listOf("BTC", "ETH", "USDT", "BNB", "USDC")
  private val currencyList = mutableListOf<Currency>()

  fun loadList(): Flow<List<Currency>> = flow {
    while (true) {
      emit(getCurrencyList())
      delay(3000)
    }
  }

  private suspend fun getCurrencyList(): List<Currency> {
    delay(3000)
    generateCurrencyList()
    return currencyList.toList()
  }

  private fun generateCurrencyList() {
    val prices = buildList {
      repeat(currencyNames.size) {
        add(Random.nextInt(1000, 2000))
      }
    }
    val newData = buildList {
      for ((index, currencyName) in currencyNames.withIndex()) {
        val price = prices[index]
        val currency = Currency(name = currencyName, price = price)
        add(currency)
      }
    }
    currencyList.clear()
    currencyList.addAll(newData)
  }
}