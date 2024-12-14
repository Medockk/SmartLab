package com.example.smartlab.di

import com.example.smartlab.feature_app.data.repository.AuthRepositoryImpl
import com.example.smartlab.feature_app.domain.repository.AuthRepository
import com.example.smartlab.feature_app.domain.usecase.Auth.SignInUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.SignUpUseCase
import org.koin.dsl.module

val createCardModule = module {

    single<AuthRepository>{
        AuthRepositoryImpl()
    }
    factory<SignInUseCase>{
        SignInUseCase(get())
    }
    factory<SignUpUseCase> {
        SignUpUseCase(get())
    }
}