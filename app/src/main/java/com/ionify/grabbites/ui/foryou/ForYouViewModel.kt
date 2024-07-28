package com.ionify.grabbites.ui.foryou

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ionify.grabbites.data.model.FoodRecommendation
import com.ionify.grabbites.data.repository.Repository
import com.ionify.grabbites.utils.Result

class ForYouViewModel(private val repository: Repository): ViewModel() {
    val fypData: LiveData<Result<FoodRecommendation>> = repository.getFyp()

    fun searchFoodData(query: String): LiveData<Result<FoodRecommendation>> = repository.getSearchFood(query)
}