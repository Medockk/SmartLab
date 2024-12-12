package com.example.smartlab.feature_app.presentation.CreatePassword.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smartlab.ui.theme._57A9FF

@Composable
fun Indicator(
    count: Int,
    currentIndicator: Int = -1,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(count){
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(if (currentIndicator in 0..3) _57A9FF else Color.White, CircleShape)
                    .border(1.dp, _57A9FF, CircleShape)
            )
            if (currentIndicator != 2){

            }
        }
    }
}