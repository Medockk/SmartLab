package com.example.smartlab.feature_app.presentation.SignIn.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.ui.theme.SF50017Black
import com.example.smartlab.ui.theme._EBEBEB

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prew() {
    EnterWithYandexButton(Modifier.padding(top = 100.dp,
        start = 20.dp, end = 20.dp)){}
}

@Composable
fun EnterWithYandexButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, _EBEBEB),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Text("Войти с Яндекс",
            style = SF50017Black)
    }
}