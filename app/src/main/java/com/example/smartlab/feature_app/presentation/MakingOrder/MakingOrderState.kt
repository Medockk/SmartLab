package com.example.smartlab.feature_app.presentation.MakingOrder

data class MakingOrderState(
    val address: String = "",
    val data: String = "",
    val person: String = "",
    val gender: String = "",
    val phone: String = "",
    val comment: String = "",

    val personClick: Boolean = false,
    val addressClick: Boolean = false,
    val dateClick: Boolean = false,

    val price: String = "",
    val proceduresCount: Int = 1,

    val isComplete: Boolean = false,

    val saveUserAddress: Boolean = false,
)
