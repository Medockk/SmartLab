package com.example.smartlab.feature_app.domain.repository

import com.example.smartlab.feature_app.domain.model.CategoryData

interface CategoryRepository {

    suspend fun getAllCategory(): List<CategoryData>
}