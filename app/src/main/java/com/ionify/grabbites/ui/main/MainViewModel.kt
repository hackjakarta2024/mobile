package com.ionify.grabbites.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ionify.grabbites.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {
    val token: Flow<String> = repository.getToken()

    fun logout() = viewModelScope.launch { repository.clearToken() }
}