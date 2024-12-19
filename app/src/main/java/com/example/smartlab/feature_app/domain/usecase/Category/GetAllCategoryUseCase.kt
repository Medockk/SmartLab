package com.example.smartlab.feature_app.domain.usecase.Category

import com.example.smartlab.feature_app.domain.model.CategoryData
import com.example.smartlab.feature_app.domain.repository.CategoryRepository

class GetAllCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(): List<CategoryData> {
        return categoryRepository.getAllCategory()
    }
}