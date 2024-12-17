package com.example.smartlab.feature_app.presentation.Payment

import android.os.CountDownTimer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PaymentViewModel: ViewModel() {

    private val _state = mutableStateOf(PaymentState())
    val state: State<PaymentState> = _state

    init {
        startTimer()
    }

    fun onEvent(event: PaymentEvent){
        when (event){
            PaymentEvent.NextPaymentState -> {
                restartTimer()
            }
        }
    }

    private fun startTimer(){
        val timer = object : CountDownTimer(4000, 15) {
            override fun onTick(millisUntilFinished: Long) {
                _state.value = state.value.copy(
                    rotate = state.value.rotate +5f
                )
            }

            override fun onFinish() {
                _state.value = state.value.copy(
                    text = "Производим оплату...",
                    isComplete = state.value.isLoading,
                    isLoading = true,
                )
            }
        }
        timer.start()
    }
    private fun restartTimer(){
        startTimer()
    }
}