package com.example.smartlab.feature_app.data.repository

import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.model.CategoryData
import com.example.smartlab.feature_app.domain.repository.CategoryRepository
import io.github.jan.supabase.postgrest.postgrest

class CategoryRepositoryImpl: CategoryRepository {

    override suspend fun getAllCategory(): List<CategoryData> {
        return client.postgrest["CatalogOfAnalyses"].select().decodeList<CategoryData>()
    }
}