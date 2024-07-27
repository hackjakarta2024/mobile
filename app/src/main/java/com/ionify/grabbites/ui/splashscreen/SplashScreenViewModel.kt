package com.ionify.grabbites.ui.splashscreen

import androidx.lifecycle.ViewModel
import com.ionify.grabbites.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class SplashScreenViewModel(private val repository: Repository): ViewModel() {
    val token: Flow<String> = repository.getToken()
}