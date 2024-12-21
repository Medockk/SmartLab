package com.example.smartlab.feature_app.presentation.Cart

sealed class CartEvent {

    data object ClearCart: CartEvent()
    data class DeleteItem(
        val procedure: String,
        val price: String,
    ): CartEvent()
    data class ChangedPatientCount(val value: Int) : CartEvent()

    data class CalculateAmount(val value: String) : CartEvent()
}