package com.example.core.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smartlab.ui.theme.SF60014White
import com.example.smartlab.ui.theme.SF60014_1A6FEE
import com.example.smartlab.ui.theme._1A6FEE

@Composable
fun CustomButton(
    title: String,
    modifier: Modifier = Modifier,
    isRemove: Boolean = false,
    removeClick: () -> Unit = {},
    onClick: () -> Unit
) {
    Button(
        onClick = if (!isRemove) onClick else removeClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (!isRemove) _1A6FEE else Color.White
        ),
        border = BorderStroke(1.dp, _1A6FEE),
        modifier = modifier
    ) {
        Text(
            text = if (!isRemove) title else {"Удалить"},
            style = if (!isRemove) SF60014White else SF60014_1A6FEE
        )
    }
}