package com.example.smartlab.feature_app.domain.usecase.Auth

import com.example.smartlab.feature_app.domain.repository.AuthRepository
import com.example.smartlab.feature_app.domain.model.UserData

class SignUpUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(mail: String, pass: String, userData: UserData){
        repository.signUp(mail, pass, userData)
    }
}