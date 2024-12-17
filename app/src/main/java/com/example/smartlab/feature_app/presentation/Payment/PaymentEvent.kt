package com.example.smartlab.feature_app.presentation.Payment

sealed class PaymentEvent {

    data object NextPaymentState: PaymentEvent()
}