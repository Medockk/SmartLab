package com.example.smartlab.EmailCode

sealed class EmailCodeEvent {

    data class EnteredCode(val value: String) : EmailCodeEvent()
    data object ResetTimer : EmailCodeEvent()
}