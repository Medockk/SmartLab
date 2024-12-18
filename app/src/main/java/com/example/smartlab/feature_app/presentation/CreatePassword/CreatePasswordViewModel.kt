package com.example.smartlab.CreatePassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.smartlab.feature_app.domain.model.UserData

class CreatePasswordViewModel: ViewModel() {

    private val _state = mutableStateOf(CreatePasswordState())
    val state: State<CreatePasswordState> = _state

    fun onEvent(event: CreatePasswordEvent){
        when (event){
            is CreatePasswordEvent.EnteredPassword -> {
                if (state.value.password.length == 3){
                    _state.value = state.value.copy(
                        isComplete = true
                    )
                }
                _state.value = state.value.copy(
                    password = state.value.password + event.value
                )
                UserData.password = state.value.password
            }
            CreatePasswordEvent.RemovePasswordItem -> {
                _state.value = state.value.copy(
                    password = state.value.password.dropLast(1)
                )
            }
        }
    }
}