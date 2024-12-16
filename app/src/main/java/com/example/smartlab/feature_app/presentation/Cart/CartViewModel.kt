package com.example.smartlab.feature_app.presentation.Cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CartViewModel: ViewModel() {

    private val _state = mutableStateOf(CartState())
    val state: State<CartState> = _state

    fun onEvent(event: CartEvent){
        when (event){
            CartEvent.AddItem -> TODO()
            CartEvent.BackClick -> TODO()
            CartEvent.ClearCart -> TODO()
            CartEvent.DeleteItem -> TODO()
            CartEvent.RegistrationClick -> TODO()
            CartEvent.RemoveItem -> TODO()
        }
    }
}