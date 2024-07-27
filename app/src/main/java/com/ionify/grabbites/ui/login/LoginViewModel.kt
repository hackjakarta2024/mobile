package com.ionify.grabbites.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ionify.grabbites.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class LoginViewModel(private val repository: Repository): ViewModel() {
    private val _loginDescText = MutableLiveData<String>().apply {
        value = "Selamat datang di <b>GrabBites</b>, silahkan login untuk memulai perjalanan bersama kami"
    }
    val loginDescText: LiveData<String> = _loginDescText

    val token: Flow<String> = repository.getToken()
    fun postLogin(email: String, password: String) = repository.postLogin(email, password)
}