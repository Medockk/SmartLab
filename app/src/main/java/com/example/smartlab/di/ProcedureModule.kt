package com.example.smartlab.di

import com.example.smartlab.feature_app.data.repository.ProcedureRepositoryImpl
import com.example.smartlab.feature_app.domain.repository.ProcedureRepository
import com.example.smartlab.feature_app.domain.usecase.Procedure.GetAllProcedureUseCase
import org.koin.dsl.module

val moduleProcedure = module {

    single<ProcedureRepository>{
        ProcedureRepositoryImpl()
    }
    factory<GetAllProcedureUseCase> {
        GetAllProcedureUseCase(get())
    }
}