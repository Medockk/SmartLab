package com.example.smartlab.feature_app.domain.repository

interface AuthRepository {
    suspend fun signIn(mail: String, pass: String)
    suspend fun signUp(mail: String, pass: String, userData: UserData)
}