package com.elka.coroutineflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elka.coroutineflow.crypto_app.CryptoActivity
import com.elka.coroutineflow.databinding.ActivityMainBinding
import com.elka.coroutineflow.lesson2.UsersActivity

class MainActivity : AppCompatActivity() {

  private val binding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    binding.buttonUsersActivity.setOnClickListener {
      startActivity(UsersActivity.newIntent(this))
    }
    binding.buttonCryptoActivity.setOnClickListener {
      startActivity(CryptoActivity.newIntent(this))
    }
  }
}