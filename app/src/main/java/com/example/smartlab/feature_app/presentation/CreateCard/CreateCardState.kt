package com.example.smartlab.CreateCard

data class CreateCardState(
    val name: String = "",
    val patronymic: String = "",
    val surname: String = "",
    val birthdayData: String = "",
    val gender: String = "",
    val isComplete: Boolean = false,
    val isLogged: Boolean = false,
)
