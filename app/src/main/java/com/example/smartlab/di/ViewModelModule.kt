package com.example.smartlab.di

import com.example.smartlab.CreateCard.CreateCardViewModel
import com.example.smartlab.CreatePassword.CreatePasswordViewModel
import com.example.smartlab.EmailCode.EmailCodeViewModel
import com.example.smartlab.MainActivityViewModel
import com.example.smartlab.OnBoard.OnBoardViewModel
import com.example.smartlab.SignIn.SignInViewModel
import com.example.smartlab.feature_app.presentation.Analuzes.AnalyzesViewModel
import com.example.smartlab.feature_app.presentation.AnalyzesCategory.AnalyzesCategoryViewModel
import com.example.smartlab.feature_app.presentation.Cart.CartViewModel
import com.example.smartlab.feature_app.presentation.MakingOrder.MakingOrderViewModel
import com.example.smartlab.feature_app.presentation.PatientCard.PatientCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleViewModel = module {

    viewModel<PatientCardViewModel>{
        PatientCardViewModel(
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel<MainActivityViewModel>{
        MainActivityViewModel(
            get(),
            get()
        )
    }
    viewModel<OnBoardViewModel>{
        OnBoardViewModel(
            get()
        )
    }
    viewModel<SignInViewModel> {
        SignInViewModel(get())
    }
    factory<EmailCodeViewModel> {
        EmailCodeViewModel(
            get(),
            get()
        )
    }
    viewModel<CreateCardViewModel>{
        CreateCardViewModel(
            get()
        )
    }
    viewModel<CreatePasswordViewModel>{
        CreatePasswordViewModel(get())
    }
    viewModel<AnalyzesViewModel>{
        AnalyzesViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel<CartViewModel>{
        CartViewModel(
            get(),
            get(),
            get()
        )
    }
    viewModel<MakingOrderViewModel>{
        MakingOrderViewModel(
            get(),
            get(),
            get()
        )
    }
    viewModel<AnalyzesCategoryViewModel>{
        AnalyzesCategoryViewModel(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}