package com.example.smartlab.feature_app.domain.usecase.Auth

import com.example.smartlab.feature_app.domain.repository.AuthRepository

class VerifyEmailUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(mail: String, tokenOTP: String){
        authRepository.verifyEmail(
            mail = mail,
            tokenOTP = tokenOTP
        )
    }
}