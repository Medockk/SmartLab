package com.example.smartlab.CreateCard

sealed class CreateCardEvent {

    data class EnteredName(val value: String) : CreateCardEvent()
    data class EnteredSurname(val value: String): CreateCardEvent()
    data class EnteredPatronymic(val value: String): CreateCardEvent()
    data class EnteredBirthdayData(val value: String): CreateCardEvent()
    data class EnteredGender(val value: String): CreateCardEvent()

    data object CreateCard: CreateCardEvent()
}