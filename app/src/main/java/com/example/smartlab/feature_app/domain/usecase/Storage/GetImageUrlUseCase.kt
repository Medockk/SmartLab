package com.example.smartlab.feature_app.domain.usecase.Storage

import com.example.smartlab.feature_app.domain.repository.StorageRepository

class GetImageUrlUseCase(
    private val storageRepository: StorageRepository
) {

    suspend operator fun invoke() : String{
        return storageRepository.getImageUrl()
    }
}