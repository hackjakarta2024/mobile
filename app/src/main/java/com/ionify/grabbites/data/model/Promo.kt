package com.ionify.grabbites.data.model

import com.google.gson.annotations.SerializedName

data class Promo(
    @field:SerializedName("promo_id")
    val promoId: String,

    @field:SerializedName("name")
    val name: String
)
