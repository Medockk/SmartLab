package com.example.smartlab.di

import com.example.smartlab.feature_app.data.repository.CartRepositoryImpl
import com.example.smartlab.feature_app.domain.repository.CartRepository
import com.example.smartlab.feature_app.domain.usecase.Cart.AddProcedureInCartUseCase
import com.example.smartlab.feature_app.domain.usecase.Cart.GetAllUserItemFromCartUseCase
import com.example.smartlab.feature_app.domain.usecase.Cart.RemoveItemFromCartUseCase
import org.koin.dsl.module

val moduleCart = module {
    single<CartRepository>{
        CartRepositoryImpl()
    }
    factory<AddProcedureInCartUseCase>{
        AddProcedureInCartUseCase(get())
    }
    factory<RemoveItemFromCartUseCase> {
        RemoveItemFromCartUseCase(get())
    }
    factory<GetAllUserItemFromCartUseCase> {
        GetAllUserItemFromCartUseCase(get())
    }
}