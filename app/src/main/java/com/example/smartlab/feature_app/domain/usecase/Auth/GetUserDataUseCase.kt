package com.example.smartlab.feature_app.domain.usecase.Auth

import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.feature_app.domain.repository.AuthRepository

class GetUserDataUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() : List<UserData>{
        return authRepository.getUserData()
    }
}