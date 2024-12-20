package com.example.smartlab.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    val id: Int = 0,
    val userID: String = "",
    val procedure: String = "",
    val price: String = "",
)