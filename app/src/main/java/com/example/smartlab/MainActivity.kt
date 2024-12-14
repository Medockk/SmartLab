package com.example.smartlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartlab.di.createCardModule
import com.example.smartlab.feature_app.presentation.PatientCard.PatientCardScreen
import com.example.smartlab.ui.theme.SmartLabTheme
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = viewModel()
            SmartLabTheme{
                PatientCardScreen()
            }
        }
    }
}