package com.example.smartlab.SignIn

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.feature_app.domain.usecase.Auth.SendOTPUseCase
import kotlinx.coroutines.launch

class SignInViewModel(
    private val sendOTPUseCase: SendOTPUseCase
): ViewModel() {

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
                UserData.email = event.value
            }

            SignInEvent.SendOTPClick -> {
                viewModelScope.launch {
                    try {
                        sendOTPUseCase(
                            mail = state.value.email
                        )
                        _state.value = state.value.copy(
                            isComplete = true,
                        )
                    } catch (e: Exception) {
                        Log.e("ex", e.message.toString())
                    }
                }
            }
        }
    }
}