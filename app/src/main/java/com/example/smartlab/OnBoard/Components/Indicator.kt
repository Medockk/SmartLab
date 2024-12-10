package com.example.smartlab.OnBoard.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartlab.ui.theme._57A9FF

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    Indicator(3, 1)
}

@Composable
fun Indicator(
    count: Int,
    currentPage: Int = 0,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count){
            val isSelected = currentPage == it
            Box(
                modifier = Modifier
                    .background(if (isSelected) _57A9FF else Color.White, CircleShape)
            )
        }
    }
}