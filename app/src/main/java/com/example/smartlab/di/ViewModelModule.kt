package com.example.smartlab.di

import com.example.smartlab.MainActivityViewModel
import com.example.smartlab.OnBoard.OnBoardViewModel
import com.example.smartlab.feature_app.presentation.PatientCard.PatientCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleViewModel = module {

    viewModel<PatientCardViewModel>{
        PatientCardViewModel(get())
    }
    viewModel<MainActivityViewModel>{
        MainActivityViewModel(get())
    }
    viewModel<OnBoardViewModel>{
        OnBoardViewModel(
            get()
        )
    }
}