package com.ionify.grabbites.data.model

import com.google.gson.annotations.SerializedName

data class FoodRecommendationResponse(

	@field:SerializedName("promo")
	val promo: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("food")
	val food: ArrayList<FoodListItem>
)