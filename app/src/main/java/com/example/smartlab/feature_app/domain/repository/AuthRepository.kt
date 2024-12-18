package com.example.smartlab.feature_app.domain.repository

import com.example.smartlab.feature_app.domain.model.UserData

interface AuthRepository {
    suspend fun signIn(mail: String, pass: String)
    suspend fun signUp(mail: String, pass: String, userData: UserData)
}
