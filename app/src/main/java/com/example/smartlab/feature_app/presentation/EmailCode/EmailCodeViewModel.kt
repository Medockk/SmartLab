package com.example.smartlab.EmailCode

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.feature_app.domain.usecase.Auth.SendOTPUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.VerifyEmailUseCase
import kotlinx.coroutines.launch

class EmailCodeViewModel(
    private val sendOTPUseCase: SendOTPUseCase,
    private val verifyEmailUseCase: VerifyEmailUseCase
) : ViewModel() {

    private val _state = mutableStateOf(EmailCodeState())
    val state: State<EmailCodeState> = _state

    init {
        timer()
    }

    private fun verifyOtp() {
        if (
            state.value.code1.isNotEmpty() && state.value.code2.isNotEmpty()
            && state.value.code3.isNotEmpty() && state.value.code4.isNotEmpty()
            && state.value.code5.isNotEmpty() && state.value.code6.isNotEmpty()
        ) {
            viewModelScope.launch {
                try {
                    verifyEmailUseCase(
                        UserData.email,
                        tokenOTP =
                        "${state.value.code1}${state.value.code2}${state.value.code3}${state.value.code4}${state.value.code5}${state.value.code6}"
                    )
                    _state.value = state.value.copy(
                        isComplete = true
                    )
                } catch (e: Exception) {
                    Log.e("err", e.message.toString())
                }
            }
        }
    }

    fun onEvent(event: EmailCodeEvent) {
        when (event) {
            is EmailCodeEvent.EnteredCode1 -> {
                if (event.value.isNotBlank() && state.value.code1.length != 1) {
                    _state.value = state.value.copy(
                        code1 = event.value
                    )
                }

                if (event.value.isEmpty()) {
                    _state.value = state.value.copy(
                        code1 = state.value.code1.dropLast(1)
                    )
                }

                verifyOtp()
            }

            EmailCodeEvent.ResetTimer -> {
                viewModelScope.launch {
                    sendOTP()
                }
                timer()
            }

            is EmailCodeEvent.EnteredCode2 -> {
                if (event.value.isNotBlank() && state.value.code2.length != 1) {
                    _state.value = state.value.copy(
                        code2 = event.value
                    )
                }

                if (event.value.isEmpty()) {
                    _state.value = state.value.copy(
                        code2 = state.value.code1.dropLast(1)
                    )
                }

                verifyOtp()
            }

            is EmailCodeEvent.EnteredCode3 -> {
                if (event.value.isNotBlank() && state.value.code3.length != 1) {
                    _state.value = state.value.copy(
                        code3 = event.value
                    )
                }

                if (event.value.isEmpty()) {
                    _state.value = state.value.copy(
                        code3 = state.value.code1.dropLast(1)
                    )
                }

                verifyOtp()
            }

            is EmailCodeEvent.EnteredCode4 -> {
                if (event.value.isNotBlank() && state.value.code4.length != 1) {
                    _state.value = state.value.copy(
                        code4 = event.value
                    )
                }

                if (event.value.isEmpty()) {
                    _state.value = state.value.copy(
                        code4 = state.value.code1.dropLast(1)
                    )
                }

                verifyOtp()
            }

            is EmailCodeEvent.EnteredCode5 -> {
                if (event.value.isNotBlank() && state.value.code5.length != 1) {
                    _state.value = state.value.copy(
                        code5 = event.value
                    )
                }

                if (event.value.isEmpty()) {
                    _state.value = state.value.copy(
                        code5 = state.value.code1.dropLast(1)
                    )
                }

                verifyOtp()
            }

            is EmailCodeEvent.EnteredCode6 -> {
                if (event.value.isNotBlank() && state.value.code6.length != 1) {
                    _state.value = state.value.copy(
                        code6 = event.value
                    )
                }

                if (event.value.isEmpty()) {
                    _state.value = state.value.copy(
                        code6 = state.value.code1.dropLast(1)
                    )
                }

                verifyOtp()
            }
        }
    }

    private suspend fun sendOTP() {
        sendOTPUseCase(
            mail = UserData.email
        )
    }

    private fun timer() {
        val timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _state.value = state.value.copy(
                    timer = (millisUntilFinished / 1000).toString()
                )
            }

            override fun onFinish() {
                _state.value = state.value.copy(
                    timer = "0"
                )
            }
        }
        timer.start()
    }
}