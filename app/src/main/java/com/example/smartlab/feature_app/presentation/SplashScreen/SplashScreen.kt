package com.example.smartlab.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme._2254F5
import com.example.smartlab.ui.theme._3740F5A6
import com.example.smartlab.ui.theme._4D9CFF
import com.example.smartlab.ui.theme._6269F00D
import com.example.smartlab.ui.theme._73D5BC
import com.example.smartlab.ui.theme._74C8E4
import com.example.smartlab.ui.theme._A1CAFF
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate(Route.OnBoardScreen.route){
            popUpTo(Route.SplashScreen.route){
                inclusive = true
            }
        }
    }
    val firstBrush = Brush.verticalGradient(
        listOf(_A1CAFF, _4D9CFF, _A1CAFF)
    )
    val secondBrush = Brush.verticalGradient(
        listOf(_74C8E4, _73D5BC, _74C8E4)
    )
    val thirdBrush = Brush.verticalGradient(
        listOf(_6269F00D, _3740F5A6, _2254F5,
                _3740F5A6, _6269F00D)
    )
    Box(
        modifier = Modifier.fillMaxSize()
            .background(firstBrush)
            .background(secondBrush)
            .background(thirdBrush),
        contentAlignment = Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Смартлаб", color = Color.White,
                fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Image(
                imageVector = ImageVector.vectorResource(com.example.smartlab.R.drawable.shape),
                contentDescription = null
            )
        }
    }
}