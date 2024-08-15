package com.example.artspace.domain

import androidx.annotation.DrawableRes

data class ArtSpaceModel(
    @DrawableRes val image: Int,
    val title: String = "",
    val description: String = "",
    val author: String = "",
    val year: String = "",
    var like: Boolean = false
)