package com.ionify.grabbites.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ionify.grabbites.ui.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(

): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel() as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
//                    Injection.provideUserRepository(context),
//                    Injection.provideCourseRepository()
                )
            }.also { instance = it }
    }
}