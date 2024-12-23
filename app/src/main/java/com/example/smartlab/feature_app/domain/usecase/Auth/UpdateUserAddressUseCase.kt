package com.example.smartlab.feature_app.domain.usecase.Auth

import com.example.smartlab.feature_app.domain.repository.AuthRepository

class UpdateUserAddressUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(newAddress: String){
        authRepository.updateUserAddress(newAddress)
    }
}