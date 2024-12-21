package com.example.smartlab.feature_app.presentation.MakingOrder

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.usecase.Auth.GetUserDataUseCase
import com.example.smartlab.feature_app.domain.usecase.Cart.GetAllUserItemFromCartUseCase
import kotlinx.coroutines.launch

class MakingOrderViewModel(
    val getUserDataUseCase: GetUserDataUseCase,
    val getAllUserItemFromCartUseCase: GetAllUserItemFromCartUseCase
): ViewModel() {

    private val _state = mutableStateOf(MakingOrderState())
    val state: State<MakingOrderState> = _state

    init {
        viewModelScope.launch {
            launch { getAllUserInformation() }
            launch { getAllUserItemFromCart() }
        }
    }

    private suspend fun getAllUserItemFromCart() {
        val userCart = getAllUserItemFromCartUseCase()
        userCart.forEach {
            _state.value = state.value.copy(
                price = if (state.value.price.isNotEmpty()){
                    (state.value.price.toInt() + it.price.toInt()).toString()
                }else{
                    it.price
                },
            )
        }
    }

    private suspend fun getAllUserInformation(){
        try {
            val userData = getUserDataUseCase()
            userData.forEach {
                _state.value = state.value.copy(
                    person = it.surname + " " + it.name,
                    gender = it.gender
                )
            }
        } catch (e: Exception) {
            Log.e("getAllUserInformationEx", e.message.toString())
        }
    }

    fun onEvent(event: MakingOrderEvent){
        when (event){
            MakingOrderEvent.AddOnMorePatient -> {
                _state.value = state.value.copy(
                    personClick = !state.value.personClick
                )
            }
            MakingOrderEvent.MakeOrder -> {
                _state.value = state.value.copy(
                    isComplete = true
                )
            }
            is MakingOrderEvent.EnteredComment -> {
                _state.value = state.value.copy(
                    comment = event.value
                )
            }
            is MakingOrderEvent.EnteredData -> {
                _state.value = state.value.copy(
                    data = event.value
                )
            }
            is MakingOrderEvent.EnteredAddress -> {
                _state.value = state.value.copy(
                    address = event.value
                )
            }
            is MakingOrderEvent.EnteredPerson -> {
                _state.value = state.value.copy(
                    person = event.value
                )
            }
            is MakingOrderEvent.EnteredPhone -> {
                _state.value = state.value.copy(
                    phone = event.value
                )
            }

            MakingOrderEvent.CompleteChanges -> {
                _state.value = state.value.copy(
                    isComplete = !state.value.isComplete
                )
            }

            MakingOrderEvent.AddressClick -> {
                _state.value = state.value.copy(
                    addressClick = !state.value.addressClick
                )
            }
            MakingOrderEvent.DateClick -> {
                _state.value = state.value.copy(
                    dateClick = !state.value.dateClick
                )
            }
        }
    }
}