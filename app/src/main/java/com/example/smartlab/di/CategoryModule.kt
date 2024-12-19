package com.example.smartlab.di

import com.example.smartlab.feature_app.data.repository.CategoryRepositoryImpl
import com.example.smartlab.feature_app.domain.repository.CategoryRepository
import com.example.smartlab.feature_app.domain.usecase.Category.GetAllCategoryUseCase
import org.koin.dsl.module

val moduleCategory = module {

    single<CategoryRepository>{
        CategoryRepositoryImpl()
    }
    factory<GetAllCategoryUseCase> {
        GetAllCategoryUseCase(get())
    }
}