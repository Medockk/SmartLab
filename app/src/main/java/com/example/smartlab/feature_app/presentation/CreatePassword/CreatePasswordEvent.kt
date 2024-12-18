package com.example.smartlab.CreatePassword

sealed class CreatePasswordEvent {

    data class EnteredPassword(val value: String): CreatePasswordEvent()

    data object RemovePasswordItem: CreatePasswordEvent()
}