package com.peter.nagy.mobsoft.homework.model

import com.google.gson.annotations.SerializedName

data class ArtistsResult(
    @SerializedName("movies")
    var artists: Movies? = null
)