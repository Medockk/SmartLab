package com.example.smartlab.EmailCode

import android.os.CountDownTimer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EmailCodeViewModel: ViewModel() {

    private val _state = mutableStateOf(EmailCodeState())
    val state: State<EmailCodeState> = _state

    init {
        timer()
    }

    fun onEvent(event: EmailCodeEvent){
        when (event){
            EmailCodeEvent.BackClick -> {

            }
            is EmailCodeEvent.EnteredCode -> {
                _state.value = state.value.copy(
                    code = event.value
                )
            }
            EmailCodeEvent.ResetTimer -> {
                timer()
            }
        }
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