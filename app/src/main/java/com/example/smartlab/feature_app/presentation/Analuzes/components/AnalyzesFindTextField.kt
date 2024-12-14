package com.example.smartlab.feature_app.presentation.Analuzes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.R
import com.example.smartlab.ui.theme.SF40016_939396
import com.example.smartlab.ui.theme._EBEBEB
import com.example.smartlab.ui.theme._F5F5F9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prev() {
    AnalyzesFindTextField(
        "", {}, ""
    )
}

@Composable
fun AnalyzesFindTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    hintText: String,
    modifier: Modifier = Modifier
) {

    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier
            .background(_F5F5F9, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, _EBEBEB),
        singleLine = true,
        decorationBox = {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 15.dp)
            ){
                if (value.isEmpty()){
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.find_icon),
                        contentDescription = "find"
                    )
                    Text(hintText, style = SF40016_939396)
                }
                it()
            }
        }
    )
}