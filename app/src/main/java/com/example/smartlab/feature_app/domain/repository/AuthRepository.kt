package com.example.smartlab.feature_app.domain.repository

import com.example.smartlab.feature_app.domain.model.UserData

interface AuthRepository {
    suspend fun signIn(mail: String, pass: String):Boolean
    suspend fun signUp(mail: String, pass: String, userData: UserData):Boolean

    suspend fun sentOTP(mail: String)
    suspend fun verifyEmail(mail: String, tokenOTP: String)

    suspend fun getUserData(): List<UserData>
    suspend fun signOut()
    suspend fun updateUserData(userData: UserData)
    suspend fun updateUserAddress(newAddress: String)
}
