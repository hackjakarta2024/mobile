package com.ionify.grabbites.data.model

import com.google.gson.annotations.SerializedName

data class UserReview(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("review")
    val review: String,

    @field:SerializedName("rating")
    val rating: Int,
)
