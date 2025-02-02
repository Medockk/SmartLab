package com.example.smartlab.CreatePassword

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.feature_app.domain.usecase.Auth.SignInUseCase
import kotlinx.coroutines.launch

class CreatePasswordViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CreatePasswordState())
    val state: State<CreatePasswordState> = _state

    fun onEvent(event: CreatePasswordEvent) {
        when (event) {
            is CreatePasswordEvent.EnteredPassword -> {

                _state.value = state.value.copy(
                    password = state.value.password + event.value
                )
                UserData.password = state.value.password

                if (state.value.password.length == 4) {
                    viewModelScope.launch {
                        try {
                            Log.v("startSignInUseCase", "startSignInUseCase")
                            val signIn = signInUseCase(
                                mail = UserData.email,
                                pass = "${UserData.password}00"
                            )

                            _state.value = state.value.copy(
                                isLogged = signIn
                            )
                            Log.v("endSignInUseCase", "endSignInUseCase")
                        } catch (e: Exception) {
                            Log.e("supaInError", "${e.message.toString()}\n" +
                                    "${UserData.email} ${UserData.password}")

                            _state.value = state.value.copy(
                                isComplete = !state.value.isLogged,
                            )
                        }
                    }
                }
            }

            CreatePasswordEvent.RemovePasswordItem -> {
                _state.value = state.value.copy(
                    password = state.value.password.dropLast(1)
                )
            }
        }
    }
}