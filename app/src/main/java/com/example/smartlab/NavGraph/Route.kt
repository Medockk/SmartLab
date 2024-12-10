package com.example.smartlab.NavGraph

sealed class Route(val route: String) {
    data object SplashScreen: Route("SplashScreen")
}