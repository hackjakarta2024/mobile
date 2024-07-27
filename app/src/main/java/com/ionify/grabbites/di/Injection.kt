package com.ionify.grabbites.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.ionify.grabbites.data.local.AuthPreference
import com.ionify.grabbites.data.remote.RetrofitConfig
import com.ionify.grabbites.data.repository.Repository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = RetrofitConfig.getApiService()
        val authPreference = AuthPreference.getInstance(context.dataStore)
        return Repository(apiService, authPreference)
    }
}