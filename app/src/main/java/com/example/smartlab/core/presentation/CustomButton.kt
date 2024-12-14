package com.example.core.presentation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartlab.ui.theme.SF60017White
import com.example.smartlab.ui.theme._1A6FEE

@Composable
fun CustomButton(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = _1A6FEE
        ),
        modifier = modifier
    ) {
        Text(
            text = title,
            style = SF60017White
        )
    }
}