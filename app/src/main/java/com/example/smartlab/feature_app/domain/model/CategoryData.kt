package com.example.smartlab.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryData(
    val id: Int = 0,
    val name: String = "",
)
