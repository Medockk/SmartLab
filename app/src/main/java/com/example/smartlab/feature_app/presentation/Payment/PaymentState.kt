package com.example.smartlab.feature_app.presentation.Payment

data class PaymentState(

    val isComplete: Boolean = false,
    val isLoading: Boolean = false,
    val rotate: Float = 0.0f,
    val text: String = "Связываемся с банком...",
)
