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

    private suspend fun calculateAmount() {
        val userCart = getAllUserItemFromCartUseCase()
        _state.value = state.value.copy(
            amount = ""
        )
        userCart.forEach {
            try {
                _state.value = state.value.copy(
                    amount = if (state.value.amount.isNotEmpty()){
                        (state.value.amount.toInt() + it.price.toInt()).toString()
                    }else{
                        it.price
                    }
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
                        amount = "",
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
                        calculateAmount()
                    } catch (e: Exception) {
                        Log.e("deleteItemEx", e.message.toString())
                    }
                }
            }

            is CartEvent.CalculateAmount -> {
                try {
                    _state.value = state.value.copy(
                        amount = if (state.value.amount.isNotEmpty()){
                            (state.value.amount.toInt()
                                    + event.value.toInt()).toString()
                        }else{
                            event.value
                        }
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