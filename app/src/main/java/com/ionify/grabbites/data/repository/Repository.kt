package com.ionify.grabbites.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.ionify.grabbites.data.local.AuthPreference
import com.ionify.grabbites.data.model.ErrorResponse
import com.ionify.grabbites.data.model.FoodRecommendation
import com.ionify.grabbites.data.model.LoginBody
import com.ionify.grabbites.data.remote.ApiService
import com.ionify.grabbites.utils.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class Repository(
    private val apiService: ApiService,
    private val authPreference: AuthPreference
) {
    suspend fun saveToken(token: String) {
        authPreference.saveToken(token)
    }

    fun getToken(): Flow<String> = authPreference.getToken()

    suspend fun clearToken() {
        authPreference.clearToken()
    }

    fun postLogin(email: String, password: String): LiveData<Result<String>> = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.login(LoginBody(email, password))
            val token = response.token
            saveToken(token)

            Log.d("Success", "Save token and profile")

            emit(Result.Success("Login success."))
        } catch (e: Exception) {
            Log.e("Login error", "Erroorrr")
            when (e) {
                is HttpException -> {
                    val jsonRes = Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
                    val msg = jsonRes.message
                    Log.e("Login error", "HTTPException ${msg}")
                    emit(Result.Error(msg))
                }
                else -> {
                    Log.e("Login error", "Another error")
                    emit(Result.Error(e.message.toString()))
                }
            }
        }
    }

    fun getFyp(): LiveData<Result<FoodRecommendation>> = liveData {
        emit(Result.Loading)

        getToken().collect { token ->
            try {
                val response = apiService.getFyp("Bearer ${token}")
                val data = response.data

                Log.d("Success", "Get food fyp")

                emit(Result.Success(data))
            } catch (e: Exception) {
                Log.e("Fetch fyp error", "Erroorrr")
                when (e) {
                    is HttpException -> {
                        val jsonRes = Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)
                        val msg = jsonRes.message
                        Log.e("Login error", "HTTPException ${msg}")
                        emit(Result.Error(msg))
                    }
                    else -> {
                        Log.e("Login error", "Another error")
                        emit(Result.Error(e.message.toString()))
                    }
                }
            }
        }
    }
}