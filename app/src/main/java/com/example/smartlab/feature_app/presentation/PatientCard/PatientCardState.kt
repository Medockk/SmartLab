package com.example.smartlab.feature_app.presentation.PatientCard

import com.example.smartlab.CreateCard.CreateCardEvent

data class PatientCardState(

    val name: String = "",
    val surname: String = "",
    val patronymic: String = "",
    val birthdayData: String = "",
    val gender: String = "",
    val image: String = "",

    val email: String = "andreev.arsenij2020@gmail.com",
    val password: String = "password",
)
