package com.example.smartlab.CustomView

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.ui.theme.SF60017White
import com.example.smartlab.ui.theme._1A6FEE

@Preview
@Composable
private fun prev() {
    CustomButton("Start") {

    }
}

@Composable
fun CustomButton(
    title: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = _1A6FEE
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = SF60017White
        )
    }
}