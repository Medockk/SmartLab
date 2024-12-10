package com.example.smartlab.OnBoard

import androidx.annotation.DrawableRes

data class OnBoardItem(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)
