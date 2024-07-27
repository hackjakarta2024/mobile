package com.ionify.grabbites.data.model

import com.google.gson.annotations.SerializedName

data class FoodListItem(

	@field:SerializedName("rating_total")
	val ratingTotal: Int,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("real_price")
	val realPrice: Int,

	@field:SerializedName("restaurant_name")
	val restaurantName: String,

	@field:SerializedName("desc")
	val desc: String,

	@field:SerializedName("fake_price")
	val fakePrice: Int,

	@field:SerializedName("user_review")
	val userReview: ArrayList<UserReview>
)