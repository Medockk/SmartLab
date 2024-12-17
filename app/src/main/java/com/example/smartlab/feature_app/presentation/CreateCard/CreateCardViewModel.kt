package com.example.smartlab.CreateCard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CreateCardViewModel: ViewModel() {
    private val _state =
        mutableStateOf(CreateCardState())
    val state: State<CreateCardState> = _state

    fun onEvent(event: CreateCardEvent){
        when (event){
            is CreateCardEvent.EnteredName -> {
                _state.value = state.value.copy(
                    name = event.value
                )
            }
            is CreateCardEvent.EnteredBirthdayData -> {
                _state.value = state.value.copy(
                    birthdayData = event.value
                )
            }
            is CreateCardEvent.EnteredGender -> {
                _state.value = state.value.copy(
                    gender = event.value
                )
            }
            is CreateCardEvent.EnteredPatronymic -> {
                _state.value = state.value.copy(
                    patronymic = event.value
                )
            }
            is CreateCardEvent.EnteredSurname -> {
                _state.value = state.value.copy(
                    surname = event.value
                )
            }
            CreateCardEvent.CreateCard -> {
                _state.value = state.value.copy(
                    isComplete = true
                )
            }
        }
    }
}