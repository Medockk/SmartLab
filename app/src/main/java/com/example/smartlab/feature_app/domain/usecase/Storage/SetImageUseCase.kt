package com.example.smartlab.feature_app.domain.usecase.Storage

import com.example.smartlab.feature_app.domain.repository.StorageRepository

class SetImageUseCase(
    private val storageRepository: StorageRepository
) {

    suspend operator fun invoke(image: ByteArray){
        storageRepository.setImage(image)
    }
}