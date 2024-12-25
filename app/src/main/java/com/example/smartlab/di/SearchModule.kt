package com.example.smartlab.di

import com.example.smartlab.feature_app.data.repository.SearchRepositoryImpl
import com.example.smartlab.feature_app.domain.repository.SearchRepository
import com.example.smartlab.feature_app.domain.usecase.Search.SearchAnalyzesUseCase
import org.koin.dsl.module

val moduleSearch = module {
    single<SearchRepository> {
        SearchRepositoryImpl()
    }
    factory<SearchAnalyzesUseCase> {
        SearchAnalyzesUseCase(get())
    }
}