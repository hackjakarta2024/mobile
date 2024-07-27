package com.ionify.grabbites.data.remote

import com.ionify.grabbites.data.model.FoodRecommendationResponse
import com.ionify.grabbites.data.model.LoginBody
import com.ionify.grabbites.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    // Login
    @POST("api/v1/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): LoginResponse

    // Get FYP
    @GET("api/v1/food/fyp")
    suspend fun getFyp(
        @Header("Authorization") token: String
    ): FoodRecommendationResponse
}