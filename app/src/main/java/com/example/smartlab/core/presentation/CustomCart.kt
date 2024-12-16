package com.example.smartlab.core.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.R
import com.example.smartlab.ui.theme.SF50016Black
import com.example.smartlab.ui.theme.SF50017Black
import com.example.smartlab.ui.theme._7E7E9A
import com.example.smartlab.ui.theme._939396
import com.example.smartlab.ui.theme._B8C1CC
import com.example.smartlab.ui.theme._F4F4F4

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prev() {
    CustomCart(
        "Клинический анализ крови с\nлейкоцитарной формулировкой",
        "690 ₽",
        {},{},{}, Modifier.fillMaxWidth().padding(top = 100.dp, start = 20.dp, end = 20.dp)
    )
}

@Composable
fun CustomCart(
    title: String,
    price: String,
    deleteClick: () -> Unit,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(1.dp),
        border = BorderStroke(1.dp, _F4F4F4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            Row {
                Text(title, style = SF50016Black)
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "delete item",
                    tint = _7E7E9A,
                    modifier = Modifier.clickable { deleteClick() }
                )
            }
            Row {
                Text(price, style = SF50017Black)
                Spacer(Modifier.weight(1f))
                Row {
                    Text("1 patient")
                    Row (
                        horizontalArrangement = Arrangement.Center
                    ){
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.minus),
                            contentDescription = "remove",
                            tint = _B8C1CC
                        )
                        VerticalDivider(Modifier.height(IntrinsicSize.Max))
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add",
                            tint = _939396
                        )
                    }
                }
            }
        }
    }
}