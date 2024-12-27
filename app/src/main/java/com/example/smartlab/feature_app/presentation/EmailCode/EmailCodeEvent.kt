package com.example.smartlab.EmailCode

sealed class EmailCodeEvent {

    data object ResetTimer : EmailCodeEvent()

    data class EnteredCode1(val value: String) : EmailCodeEvent()
    data class EnteredCode2(val value: String) : EmailCodeEvent()
    data class EnteredCode3(val value: String) : EmailCodeEvent()
    data class EnteredCode4(val value: String) : EmailCodeEvent()
    data class EnteredCode5(val value: String) : EmailCodeEvent()
    data class EnteredCode6(val value: String) : EmailCodeEvent()
}