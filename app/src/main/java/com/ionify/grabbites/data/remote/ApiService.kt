package com.ionify.grabbites.data.remote

import com.ionify.grabbites.data.model.LoginBody
import com.ionify.grabbites.data.model.LoginResponse
import retrofit2.http.Body

interface ApiService {
    // Login
    suspend fun login(
        @Body loginBody: LoginBody
    ): LoginResponse
}