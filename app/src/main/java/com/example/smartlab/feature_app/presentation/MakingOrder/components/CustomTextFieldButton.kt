package com.example.smartlab.feature_app.presentation.MakingOrder.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.ui.theme.SF40015Black
import com.example.smartlab.ui.theme._EBEBEB
import com.example.smartlab.ui.theme._F5F5F9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    CustomTextFieldButton(
        "Adfghtr",
        buttonModifier = Modifier.fillMaxWidth(),
        boxModifier = Modifier.fillMaxWidth()
            .padding(top = 100.dp, start = 20.dp, end = 20.dp)
            .height(50.dp)
    ){

    }
}

@Composable
fun CustomTextFieldButton(
    title: String,
    buttonModifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = boxModifier
    ){
        Button(
            modifier = buttonModifier,
            onClick = onClick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = _F5F5F9),
            border = BorderStroke(1.dp, _EBEBEB)
        ){}
        Text(title, style = SF40015Black,
            modifier = Modifier.padding(start = 15.dp))
    }
}