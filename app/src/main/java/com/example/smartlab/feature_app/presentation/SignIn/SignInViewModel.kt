package com.example.smartlab.SignIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.smartlab.feature_app.domain.model.UserData

class SignInViewModel: ViewModel() {

    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> = _state

    fun onEvent(event: SignInEvent){
        when (event){
            SignInEvent.EnterWithYandex -> {

            }
            is SignInEvent.EnteredEmail -> {
                _state.value = state.value.copy(
                    email = event.value
                )
                UserData.email = state.value.email
            }
        }
    }
}