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
        }
    }
}