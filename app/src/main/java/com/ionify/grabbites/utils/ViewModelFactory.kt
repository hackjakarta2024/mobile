package com.ionify.grabbites.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ionify.grabbites.data.repository.Repository
import com.ionify.grabbites.di.Injection
import com.ionify.grabbites.ui.foryou.ForYouViewModel
import com.ionify.grabbites.ui.login.LoginViewModel
import com.ionify.grabbites.ui.main.MainViewModel
import com.ionify.grabbites.ui.splashscreen.SplashScreenViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val repository: Repository,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository) as T
            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) -> SplashScreenViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(ForYouViewModel::class.java) -> ForYouViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}