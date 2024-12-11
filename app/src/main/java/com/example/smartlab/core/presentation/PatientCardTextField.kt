package com.example.smartlab.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.ui.theme._EBEBEB
import com.example.smartlab.ui.theme._F5F5F9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prev() {
    PatientCardTextField(
        "",{},""
    )
}

@Composable
fun PatientCardTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    hintText: String,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = Modifier
            .fillMaxWidth()
            .background(_F5F5F9, RoundedCornerShape(10.dp))
            .border(1.dp, _EBEBEB)
            .then(modifier),
        singleLine = true,

    )
}