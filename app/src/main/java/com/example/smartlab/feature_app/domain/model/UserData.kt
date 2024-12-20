package com.example.smartlab.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: Int = 0,
    val name: String,
    val surname: String,
    val patronymic: String,
    val birthdayData: String,
    val gender: String,
    val userID: String = "",
){
    companion object{
        val male = "Мужской"
        val female = "Женский"
        var password: String = ""
        var email: String = ""
    }
}
