package com.example.smartlab.di

import com.example.smartlab.feature_app.data.repository.StorageRepositoryImpl
import com.example.smartlab.feature_app.domain.repository.StorageRepository
import com.example.smartlab.feature_app.domain.usecase.Storage.GetImageUrlUseCase
import com.example.smartlab.feature_app.domain.usecase.Storage.SetImageUseCase
import org.koin.dsl.module

val moduleStorage = module {

    single<StorageRepository> {
        StorageRepositoryImpl()
    }
    factory<GetImageUrlUseCase> {
        GetImageUrlUseCase(get())
    }
    factory<SetImageUseCase> {
        SetImageUseCase(get())
    }
}