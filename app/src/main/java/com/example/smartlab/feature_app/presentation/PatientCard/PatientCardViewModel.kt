package com.example.smartlab.feature_app.presentation.PatientCard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.repository.UserData
import com.example.smartlab.feature_app.domain.usecase.Auth.SignUpUseCase
import kotlinx.coroutines.launch

class PatientCardViewModel(
//    private val useCase: SignUpUseCase
): ViewModel() {

    private val _state = mutableStateOf(PatientCardState())
    val state: State<PatientCardState> = _state

    fun onEvent(event: PatientCardOnEvent){

        when (event){
            is PatientCardOnEvent.EnteredBirthdayData -> {
                _state.value = state.value.copy(
                    birthdayData = event.value
                )
            }
            is PatientCardOnEvent.EnteredGender -> {
                _state.value = state.value.copy(
                    gender = event.value
                )
            }
            is PatientCardOnEvent.EnteredName -> {
                _state.value = state.value.copy(
                    name = event.value
                )
            }
            is PatientCardOnEvent.EnteredPatronymic -> {
                _state.value = state.value.copy(
                    patronymic = event.value
                )
            }
            is PatientCardOnEvent.EnteredSurname -> {
                _state.value = state.value.copy(
                    surname = event.value
                )
            }

            PatientCardOnEvent.AnalyzesClick -> TODO()
            PatientCardOnEvent.ResultsClick -> TODO()
            PatientCardOnEvent.SaveClick -> {
//                viewModelScope.launch{
//                    try {
//                        useCase(
//                            state.value.email,
//                            state.value.password,
//                            UserData(
//                                state.value.name,state.value.surname,
//                                state.value.patronymic, state.value.birthdayData,
//                                state.value.gender
//                            )
//                        )
//                    } catch (_: Exception) {
//
//                    }
//                }
            }
            PatientCardOnEvent.SupportsClick -> TODO()
        }
    }
}