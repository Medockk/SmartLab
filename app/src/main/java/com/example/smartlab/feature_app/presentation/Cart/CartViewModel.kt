package com.example.smartlab.feature_app.presentation.Cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.usecase.Cart.GetAllUserItemFromCartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(
    private val getAllUserItemFromCartUseCase: GetAllUserItemFromCartUseCase
): ViewModel() {

    private val _state = mutableStateOf(CartState())
    val state: State<CartState> = _state

    init {
        viewModelScope.launch {
            launch { getAllUserItemFromCart() }
        }
    }

    private suspend fun getAllUserItemFromCart(){
        val userCart = getAllUserItemFromCartUseCase()

        withContext(Dispatchers.IO){
            _state.value = state.value.copy(
                userCart = userCart
            )
        }
    }

    fun onEvent(event: CartEvent){
        when (event){
            CartEvent.AddItem -> {

            }
            CartEvent.ClearCart -> {
                _state.value = state.value.copy(
                    userCart = emptyList()
                )
            }
            CartEvent.DeleteItem -> {

            }
            CartEvent.RemoveItem -> {

            }

            is CartEvent.CalculateAmount -> {
                _state.value = state.value.copy(
                    amount = state.value.amount
                            + event.value.toInt()
                )
            }
        }
    }
}