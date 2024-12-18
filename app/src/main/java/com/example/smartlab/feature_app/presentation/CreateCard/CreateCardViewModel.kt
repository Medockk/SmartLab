package com.example.smartlab.CreateCard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.feature_app.domain.usecase.Auth.SignUpUseCase
import kotlinx.coroutines.launch

class CreateCardViewModel (
    private val useCase: SignUpUseCase
): ViewModel() {

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
                viewModelScope.launch {
                    try {
                        useCase(
                            mail = UserData.email,
                            pass = UserData.password,
                            userData = UserData(
                                name = state.value.name,
                                surname = state.value.surname,
                                patronymic = state.value.patronymic,
                                birthdayData = state.value.birthdayData,
                                gender = state.value.gender,
                            )
                        )

                        _state.value = state.value.copy(
                            isComplete = true
                        )
                    } catch (e: Exception) {
                        Log.e("supa", e.message.toString())
                    }
                }
            }
        }
    }
}