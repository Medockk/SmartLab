package com.example.smartlab.feature_app.domain.usecase.Auth

import com.example.smartlab.feature_app.domain.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(mail: String, pass: String){
        repository.signIn(mail, pass)
    }
}