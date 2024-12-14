package com.example.smartlab.feature_app.presentation.Analuzes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.CustomButton
import com.example.smartlab.ui.theme.SF50016Black
import com.example.smartlab.ui.theme.SF60014Black
import com.example.smartlab.ui.theme.SF60014_939396
import com.example.smartlab.ui.theme._F4F4F4

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prev() {
    AnalyzesCatalog(Modifier, "ПЦР-тест на определение РНК\nкоронавируса стандартный", "", "") {}
}

@Composable
fun AnalyzesCatalog(
    modifier: Modifier = Modifier,
    title: String,
    data: String,
    price: String,
    buttonClick: (Boolean) -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(1.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, _F4F4F4)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, style = SF50016Black)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(data, style = SF60014_939396)
                    Text(price, style = SF60014Black)
                }
                Spacer(Modifier.weight(1f))
                CustomButton(
                    "Добавить"
                ) {
                    val addProcedure = mutableStateOf(false)
                    val state = !addProcedure.value
                    buttonClick(state)
                }
            }
        }
    }

}
