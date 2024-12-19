package com.example.smartlab.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ListOfProcedures(
    val id: Int = 0,
    val name: String = "",
    val time: String = "",
    val price: String = "",
)
