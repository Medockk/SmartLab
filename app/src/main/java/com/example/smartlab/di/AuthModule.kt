package com.example.smartlab.di

import com.example.smartlab.feature_app.data.repository.AuthRepositoryImpl
import com.example.smartlab.feature_app.domain.repository.AuthRepository
import com.example.smartlab.feature_app.domain.usecase.Auth.GetUserDataUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.SignInUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.SignOutUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.SignUpUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.UpdateUserAddressUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.UpdateUserDataUseCase
import org.koin.dsl.module

val moduleAuth = module {

    single<AuthRepository>{
        AuthRepositoryImpl()
    }
    factory<SignInUseCase>{
        SignInUseCase(get())
    }
    factory<SignUpUseCase> {
        SignUpUseCase(get())
    }
    factory<GetUserDataUseCase>{
        GetUserDataUseCase(get())
    }
    factory<SignOutUseCase>{
        SignOutUseCase(get())
    }
    factory<UpdateUserDataUseCase> {
        UpdateUserDataUseCase(get())
    }
    factory<UpdateUserAddressUseCase> {
        UpdateUserAddressUseCase(get())
    }
}