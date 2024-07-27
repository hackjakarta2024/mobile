package com.ionify.grabbites.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(@field:SerializedName("message") val message: String)