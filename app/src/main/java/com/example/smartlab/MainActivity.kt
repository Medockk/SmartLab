package com.example.smartlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.CreateCard.CreateCardScreen
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = viewModel()
            val navController = rememberNavController()
            SmartLabTheme{
                NavHost(navController, startDestination = Route.CartScreen.route){
                    composable(Route.SplashScreen.route){
                        SplashScreen(navController)
                    }
                    composable(Route.OnBoardScreen.route){
                        OnBoardScreen(navController)
                    }
                    composable(Route.SignInScreen.route){
                        SignInScreen(navController)
                    }
                    composable(Route.EmailCodeScreen.route){
                        EmailCodeScreen(navController)
                    }
                    composable(Route.CreatePasswordScreen.route){
                        CreateCardScreen(navController)
                    }
                    composable(Route.AnalyzesScreen.route){
                        AnalyzesScreen(navController)
                    }
                    composable(Route.AnalyzesCategoryScreen.route){
                        AnalyzesCategoryScreen(navController)
                    }
                    composable(Route.PatientCardScreen.route){
                        PatientCardScreen(navController)
                    }
                    composable(Route.CartScreen.route){
                        CartScreen(navController)
                    }
                    composable(Route.MakingOrderScreen.route){
                        MakingOrderScreen(navController)
                    }
                    composable(Route.PaymentScreen.route){
                        PaymentScreen()
                    }
                }
            }
        }
    }
}