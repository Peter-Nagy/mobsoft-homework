package com.peter.nagy.mobsoft.homework.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("name")
    var name: String? = null
)