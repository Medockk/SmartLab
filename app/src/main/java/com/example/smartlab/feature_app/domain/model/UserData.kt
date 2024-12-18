package com.example.smartlab.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: String = "",
    val name: String,
    val surname: String,
    val patronymic: String,
    val birthdayData: String,
    val gender: String,
){
    companion object{
        val genderList = listOf("Мужской", "Женский")
        var password: String = ""
        var email: String = ""
    }
}
