package com.example.smartlab.feature_app.presentation.MakingOrder

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MakingOrderViewModel: ViewModel() {

    private val _state = mutableStateOf(MakingOrderState())
    val state: State<MakingOrderState> = _state

    fun onEvent(event: MakingOrderEvent){
        when (event){
            MakingOrderEvent.AddOnMorePatient -> {

            }
            MakingOrderEvent.BackClick -> {

            }
            MakingOrderEvent.MakeOrder -> {

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
        }
    }
}