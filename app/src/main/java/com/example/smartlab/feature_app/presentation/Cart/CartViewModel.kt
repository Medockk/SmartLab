package com.example.smartlab.feature_app.presentation.Cart

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.model.Cart
import com.example.smartlab.feature_app.domain.usecase.Cart.GetAllUserItemFromCartUseCase
import com.example.smartlab.feature_app.domain.usecase.Cart.RemoveAllItemFromCartUseCase
import com.example.smartlab.feature_app.domain.usecase.Cart.RemoveItemFromCartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(
    private val getAllUserItemFromCartUseCase: GetAllUserItemFromCartUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase,
    private val removeAllItemFromCartUseCase: RemoveAllItemFromCartUseCase,
): ViewModel() {

    private val _state = mutableStateOf(CartState())
    val state: State<CartState> = _state

    init {
        viewModelScope.launch {
            launch {
                getAllUserItemFromCart()
                calculateAmount()
            }
        }
    }

    private fun calculateAmount() {
        val userCart = state.value.userCart
        userCart.forEach {
            try {
                _state.value = state.value.copy(
                    amount = state.value.amount + it.price.toInt()
                )
            } catch (e: Exception) {
                Log.e("calculateAmountEx", e.message.toString())
            }
        }
    }

    private suspend fun getAllUserItemFromCart(){
        val userCart = getAllUserItemFromCartUseCase()

        withContext(Dispatchers.IO){
            _state.value = state.value.copy(
                userCart = userCart,
                buttonEnabled = userCart.isNotEmpty()
            )
        }
    }

    fun onEvent(event: CartEvent){
        when (event){
            CartEvent.ClearCart -> {
                viewModelScope.launch {
                    removeAllItemFromCartUseCase()
                    _state.value = state.value.copy(
                        userCart = emptyList(),
                        amount = 0,
                        buttonEnabled = false
                    )
                }
            }
            is CartEvent.DeleteItem -> {
                viewModelScope.launch {
                    try {
                        removeItemFromCartUseCase(
                            Cart(
                                procedure = event.procedure
                            )
                        )
                        getAllUserItemFromCart()
                        _state.value = state.value.copy(
                            amount = state.value.amount - event.price.toInt()
                        )
                    } catch (e: Exception) {
                        Log.e("deleteItemEx", e.message.toString())
                    }
                }
            }

            is CartEvent.CalculateAmount -> {
                try {
                    _state.value = state.value.copy(
                        amount = state.value.amount
                                + event.value.toInt()
                    )
                } catch (e: Exception) {
                    Log.e("calculateAmountEx", e.message.toString())
                }
            }

            is CartEvent.ChangedPatientCount -> {

            }
        }
    }
}