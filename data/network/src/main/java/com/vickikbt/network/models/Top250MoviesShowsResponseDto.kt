package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class Top250MoviesShowsResponseDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,

    @SerializedName("items")
    val top250MovieShows: List<Top250MovieShowDto>?
)