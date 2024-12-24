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
): ViewModel() {

    private val _state = mutableStateOf(EmailCodeState())
    val state: State<EmailCodeState> = _state

    init {
        timer()

    }

    fun onEvent(event: EmailCodeEvent){
        when (event){
            is EmailCodeEvent.EnteredCode -> {
                _state.value = state.value.copy(
                    code = event.value
                )
                if (state.value.code.length == 6){
                    viewModelScope.launch {
                        try {
                            verifyEmailUseCase(
                                UserData.email,
                                tokenOTP = state.value.code
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
            EmailCodeEvent.ResetTimer -> {
                viewModelScope.launch {
                    sendOTP()
                }
                timer()
            }
        }
    }

    private suspend fun sendOTP(){
        sendOTPUseCase(
            mail = UserData.email
        )
    }

    private fun timer(){
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