
package com.example.smartlab.feature_app.domain.repository

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val name: String,
    val surname: String,
    val patronymic: String,
    val birthdayData: String,
    val gender: String,
){
    companion object{
        val genderList = listOf("Мужской", "Женский")
    }
}
