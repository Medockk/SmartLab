package com.example.smartlab.navGraph

sealed class Route(val route: String) {

    data object SplashScreen: Route("SplashScreen")
    data object OnBoardScreen: Route("OnBoardScreen")
    data object SignInScreen: Route("SignInScreen")
    data object EmailCodeScreen: Route("EmailCodeScreen")
    data object CreatePasswordScreen: Route("CreatePasswordScreen")
    data object CreateCardScreen: Route("CreateCardScreen")
    data object AnalyzesScreen: Route("AnalyzesScreen")
    data object AnalyzesCategoryScreen: Route("AnalyzesCategoryScreen")
    data object PatientCardScreen: Route("PatientCardScreen")
    data object CartScreen: Route("CartScreen")
    data object MakingOrderScreen: Route("MakingOrderScreen")
    data object PaymentScreen: Route("PaymentScreen")
}