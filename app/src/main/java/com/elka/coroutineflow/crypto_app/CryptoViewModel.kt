package com.elka.coroutineflow.crypto_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

  private val repository = CryptoRepository

  private val _state = MutableLiveData<State>(State.Initial)
  val state: LiveData<State> = _state

  init {
    loadData()
  }

  private fun loadData() {
    viewModelScope.launch {
      repository.loadList()
        .onStart { _state.value = State.Loading }  // выполняется при старте записи в поток
        .filter { it.isNotEmpty() }   // фильтрация содержимого потока
        .onEach { _state.value = State.Content(currencyList = it) } // без возможности его изменить
        .collect()
    }

    // equal it
    repository.loadList()
      .onStart { _state.value = State.Loading }  // выполняется при старте записи в поток
      .filter { it.isNotEmpty() }   // фильтрация содержимого потока
      .onEach { _state.value = State.Content(currencyList = it) } // без возможности его изменить
      .launchIn(viewModelScope)
  }
}
