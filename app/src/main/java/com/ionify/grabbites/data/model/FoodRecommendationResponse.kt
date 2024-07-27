package com.ionify.grabbites.data.model

import com.google.gson.annotations.SerializedName

data class FoodRecommendationResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("data")
	val data: FoodRecommendation
)