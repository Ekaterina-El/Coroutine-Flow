package com.elka.coroutineflow.lessons.lesson2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

  private val repository = UsersRepository

  private val _users = MutableLiveData<List<String>>()
  val users: LiveData<List<String>> = _users

  init {
    loadUsers()
  }

  fun addUser(user: String) {
    viewModelScope.launch {
      UsersRepository.addUser(user)
    }
  }

  private fun loadUsers() {
    viewModelScope.launch {
      UsersRepository.loadUsers().collect {
        _users.value = it
      }
    }
  }
}