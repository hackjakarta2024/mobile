package com.ionify.grabbites.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _loginDescText = MutableLiveData<String>().apply {
        value = "Selamat datang di <b>GrabBites</b>, silahkan login untuk memulai perjalanan bersama kami"
    }
    val loginDescText: LiveData<String> = _loginDescText
}