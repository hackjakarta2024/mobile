package com.ionify.grabbites.data.remote

import com.ionify.grabbites.data.model.FoodRecommendationResponse
import com.ionify.grabbites.data.model.LoginBody
import com.ionify.grabbites.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    // Login
    @POST("api/v1/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ): LoginResponse

    // Get FYP
    @GET("api/v1/fyp/food")
    suspend fun getFyp(
        @Header("Authorization") token: String
    ): FoodRecommendationResponse

    // Get Search food
    @GET("api/v1/fyp/search")
    suspend fun getSearchFood(
        @Header("Authorization") token: String,
        @Query("query") query: String
    ): FoodRecommendationResponse
}