package com.example.smartlab.SignIn

sealed class SignInEvent {

    data class EnteredEmail(val value: String) : SignInEvent()
    data object EnterWithYandex : SignInEvent()

    data object SendOTPClick : SignInEvent()
}