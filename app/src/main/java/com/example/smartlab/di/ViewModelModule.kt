package com.example.smartlab.di

import com.example.smartlab.CreateCard.CreateCardViewModel
import com.example.smartlab.CreatePassword.CreatePasswordViewModel
import com.example.smartlab.MainActivityViewModel
import com.example.smartlab.OnBoard.OnBoardViewModel
import com.example.smartlab.feature_app.presentation.Analuzes.AnalyzesViewModel
import com.example.smartlab.feature_app.presentation.PatientCard.PatientCardViewModel
import com.example.smartlab.qViewModel
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
    viewModel<CreateCardViewModel>{
        CreateCardViewModel(get())
    }
    viewModel<CreatePasswordViewModel>{
        CreatePasswordViewModel(get())
    }
    viewModel<qViewModel>{
        qViewModel(
            get(),
            get()
        )
    }
    viewModel<AnalyzesViewModel>{
        AnalyzesViewModel(get())
    }
}