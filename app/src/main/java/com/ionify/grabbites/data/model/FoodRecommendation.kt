package com.ionify.grabbites.data.model

import com.google.gson.annotations.SerializedName

data class FoodRecommendation(
    @field:SerializedName("promo")
    val promo: Promo,

    @field:SerializedName("user_id")
    val userId: String,

    @field:SerializedName("food")
    val food: ArrayList<FoodListItem>,
)
