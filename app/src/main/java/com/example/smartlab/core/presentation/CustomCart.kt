package com.example.smartlab.core.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.R
import com.example.smartlab.ui.theme.SF40015Black
import com.example.smartlab.ui.theme.SF50016Black
import com.example.smartlab.ui.theme.SF50017Black
import com.example.smartlab.ui.theme._7E7E9A
import com.example.smartlab.ui.theme._939396
import com.example.smartlab.ui.theme._B8C1CC
import com.example.smartlab.ui.theme._EBEBEB
import com.example.smartlab.ui.theme._F4F4F4
import com.example.smartlab.ui.theme._F5F5F9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    CustomCart(
        "Клинический анализ крови с\nлейкоцитарной формулировкой",
        "690 ₽", 1, {},
        {}, Modifier.fillMaxWidth().padding(top = 100.dp, start = 20.dp, end = 20.dp)
    )
}

@Composable
fun CustomCart(
    title: String,
    price: String,
    patientCount: Int = 1,
    onPatientCountChanged: (Int) -> Unit,
    deleteProcedureClick: () -> Unit,
    cardModifier: Modifier = Modifier,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(1.dp),
        border = BorderStroke(1.dp, _F4F4F4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = cardModifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column (
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(title, style = SF50016Black,
                    modifier = Modifier.weight(0.8f))
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "delete item",
                    tint = _7E7E9A,
                    modifier = Modifier.clickable { deleteProcedureClick() }
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(price, style = SF50017Black)
                Spacer(Modifier.weight(1f))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text("$patientCount patient", style = SF40015Black)
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(32.dp)
                            .padding(start = 15.dp)
                            .background(_F5F5F9, RoundedCornerShape(8.dp))
                    ){
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.minus),
                            contentDescription = "remove",
                            tint = _B8C1CC,
                            modifier = Modifier.clickable {
                                if (patientCount >= 1){
                                    onPatientCountChanged(patientCount - 1)
                                }else{
                                    deleteProcedureClick()
                                }
                            }
                        )
                        VerticalDivider(Modifier
                            .width(1.dp).background(_EBEBEB)
                            .height(15.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add",
                            tint = _939396,
                            modifier = Modifier.clickable {
                                onPatientCountChanged(patientCount + 1)
                            }
                        )
                    }
                }
            }
        }
    }
}