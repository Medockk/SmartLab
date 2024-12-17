package com.example.smartlab.core.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.ui.theme.SF40015_1A6FEE
import com.example.smartlab.ui.theme._1A6FEE

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {

}

@Composable
fun CustomEmptyButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, _1A6FEE)
    ) {
        Text(title, style = SF40015_1A6FEE)
    }
}