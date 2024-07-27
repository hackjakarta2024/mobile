package com.ionify.grabbites.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreference private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_VALUE] = token
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_VALUE] ?: ""
        }
    }

    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences[TOKEN_VALUE] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AuthPreference? = null
        private val TOKEN_VALUE = stringPreferencesKey("token")

        fun getInstance(dataStore: DataStore<Preferences>): AuthPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = AuthPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}