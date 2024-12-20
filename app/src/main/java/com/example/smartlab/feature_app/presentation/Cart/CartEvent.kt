package com.example.smartlab.feature_app.presentation.Cart

sealed class CartEvent {

    data object ClearCart: CartEvent()
    data object DeleteItem: CartEvent()
    data object AddItem: CartEvent()
    data object RemoveItem: CartEvent()

    data class CalculateAmount(val value: String) : CartEvent()
}