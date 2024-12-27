package com.example.smartlab

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.CreateCard.CreateCardScreen
import com.example.smartlab.CreatePassword.CreatePasswordScreen
import com.example.smartlab.EmailCode.EmailCodeScreen
import com.example.smartlab.OnBoard.OnBoardScreen
import com.example.smartlab.SignIn.SignInScreen
import com.example.smartlab.SplashScreen.SplashScreen
import com.example.smartlab.feature_app.presentation.Analuzes.AnalyzesScreen
import com.example.smartlab.feature_app.presentation.AnalyzesCategory.AnalyzesCategoryScreen
import com.example.smartlab.feature_app.presentation.Cart.CartScreen
import com.example.smartlab.feature_app.presentation.MakingOrder.MakingOrderScreen
import com.example.smartlab.feature_app.presentation.PatientCard.PatientCardScreen
import com.example.smartlab.feature_app.presentation.Payment.PaymentScreen
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.SmartLabTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = koinViewModel()
            val navController = rememberNavController()
            LaunchedEffect(key1 = true) {
                viewModel.checkRoute()
            }
            SmartLabTheme {
                NavHost(navController, startDestination = Route.SplashScreen.route) {
                    composable(Route.SplashScreen.route) {
                        SplashScreen(navController)
                    }
                    composable(Route.OnBoardScreen.route) {
                        OnBoardScreen(navController)
                    }
                    composable(Route.SignInScreen.route) {
                        SignInScreen(navController)
                    }
                    composable(Route.EmailCodeScreen.route) {
                        EmailCodeScreen(navController)
                    }
                    composable(Route.CreatePasswordScreen.route) {
                        CreatePasswordScreen(navController)
                    }
                    composable(Route.CreateCardScreen.route) {
                        CreateCardScreen(navController)
                    }
                    composable(Route.AnalyzesScreen.route) {
                        AnalyzesScreen(navController)
                    }
                    composable(Route.AnalyzesCategoryScreen.route) {
                        AnalyzesCategoryScreen(navController)
                    }
                    composable(Route.PatientCardScreen.route) {
                        PatientCardScreen(navController)
                    }
                    composable(Route.CartScreen.route) {
                        CartScreen(navController)
                    }
                    composable(Route.MakingOrderScreen.route) {
                        MakingOrderScreen(navController)
                    }
                    composable(Route.PaymentScreen.route) {
                        PaymentScreen(navController)
                    }
                }
            }
        }
    }
}