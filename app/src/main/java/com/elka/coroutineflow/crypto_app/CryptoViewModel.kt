package com.elka.coroutineflow.crypto_app

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {
  private val repository = CryptoRepository

  val state: LiveData<State> = repository.loadList()
    .filter { it.isNotEmpty() }   // фильтрация содержимого потока
    .map { State.Content(it) as State }
    .onStart { emit(State.Loading) }  // выполняется при старте записи в поток
    .asLiveData()
}
