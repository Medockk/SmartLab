package com.example.smartlab.feature_app.presentation.PatientCard

import com.example.smartlab.feature_app.domain.model.UserData

data class PatientCardState(

    val name: String = "",
    val surname: String = "",
    val patronymic: String = "",
    val birthdayData: String = "",
    val gender: String = "",
    val image: String = "",

    val email: String = "",
    val password: String = "",

    val listOfUserData: List<UserData> = emptyList()
)
