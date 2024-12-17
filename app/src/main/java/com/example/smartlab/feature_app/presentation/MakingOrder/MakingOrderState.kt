package com.example.smartlab.feature_app.presentation.MakingOrder

data class MakingOrderState(
    val address: String = "",
    val data: String = "",
    val person: String = "",
    val phone: String = "",
    val comment: String = "",
    val isComplete: Boolean = false,
)
