package com.example.smartlab.feature_app.domain.usecase.Auth

import com.example.smartlab.feature_app.domain.repository.AuthRepository

class SendOTPUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(mail: String){
        authRepository.sentOTP(mail)
    }
}