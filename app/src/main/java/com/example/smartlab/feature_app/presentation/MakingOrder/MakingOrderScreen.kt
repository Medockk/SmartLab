package com.example.smartlab.feature_app.presentation.MakingOrder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.CustomButton
import com.example.smartlab.R
import com.example.smartlab.core.presentation.CustomBackIcon
import com.example.smartlab.core.presentation.CustomTextField
import com.example.smartlab.ui.theme.SF40014_939396
import com.example.smartlab.ui.theme.SF40015_1A6FEE
import com.example.smartlab.ui.theme.SF50015Black
import com.example.smartlab.ui.theme.SF50015_939396
import com.example.smartlab.ui.theme.SF50017Black
import com.example.smartlab.ui.theme.SF70024Black
import com.example.smartlab.ui.theme._1A6FEE
import com.example.smartlab.ui.theme._B8C1CC

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prev() {
    MakingOrderScreen()
}

@Composable
fun MakingOrderScreen(
    viewModel: MakingOrderViewModel = viewModel()
) {
    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = paddingTop.dp,
                start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,

    ) {
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(0.08f)
        ){
            CustomBackIcon(
                Modifier.fillMaxWidth(0.07f)
                    .fillMaxHeight(0.4f)
            ) {
                viewModel.onEvent(MakingOrderEvent.BackClick)
            }
            Text("Оформление заказа", style = SF70024Black)
        }

        LazyColumn (
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 30.dp)
        ){
            item {
                Column {
                    Text("Адрес *", style = SF40014_939396)
                    CustomTextField(
                        value = state.address,
                        onValueChanged = {
                            viewModel.onEvent(MakingOrderEvent.EnteredAddress(it))
                        },
                        hintText = "Введите ваш адрес",
                        modifier = Modifier.fillParentMaxHeight(0.07f)
                    )
                }
            }
            item {
                Column {
                    Text("Дата и время*", style = SF40014_939396)
                    CustomTextField(
                        value = state.data,
                        onValueChanged = {
                            viewModel.onEvent(MakingOrderEvent.EnteredData(it))
                        },
                        hintText = "Выберите дату и время",
                        modifier = Modifier.fillParentMaxHeight(0.07f)
                    )
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillParentMaxHeight(0.2f)
                ) {
                    Text("Кто будет сдавать анализы? *", style = SF40014_939396)
                    CustomTextField(
                        value = state.person,
                        onValueChanged = {
                            viewModel.onEvent(MakingOrderEvent.EnteredPerson(it))
                        },
                        hintText = "Тицкий Эдуард",
                        modifier = Modifier.fillParentMaxHeight(0.07f)
                    )
                    Button(
                        onClick = {viewModel.onEvent(MakingOrderEvent.AddOnMorePatient)},
                        modifier = Modifier.fillParentMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, _1A6FEE)
                    ) {
                        Text("Добавить еще пациента", style = SF40015_1A6FEE)
                    }
                }
            }
            item {
                Column {
                    Text("Телефон *", style = SF40014_939396)
                    CustomTextField(
                        value = state.phone,
                        onValueChanged = {
                            viewModel.onEvent(MakingOrderEvent.EnteredPhone(it))
                        },
                        hintText = "+7 (967) 078-58-37",
                        modifier = Modifier.fillParentMaxHeight(0.07f)
                    )
                }
            }
            item {
                Column {
                    Row {
                        Text("Комментарий", style = SF40014_939396)
                        Spacer(Modifier.weight(1f))
                        Image(
                            painter = painterResource(R.drawable.male),
                            contentDescription = "microphone",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(24.dp, 20.dp)
                        )
                    }
                    CustomTextField(
                        value = state.comment,
                        onValueChanged = {
                            viewModel.onEvent(MakingOrderEvent.EnteredComment(it))
                        },
                        hintText = "Можете оставить свои пожелания",
                        modifier = Modifier.fillParentMaxHeight(0.15f)
                    )
                }
            }
            item {
                Row {
                    Text("Оплата Apple Pay",
                        style = SF50015Black)
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = _B8C1CC
                    )
                }
            }
            item {
                Row {
                    Text("Промокод",
                        style = SF50015_939396)
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = _B8C1CC
                    )
                }
            }
            item {
                Row {
                    Text("1 анализ",
                        style = SF50017Black)
                    Spacer(Modifier.weight(1f))
                    Text("690 ₽",
                        style = SF50017Black)
                }
            }
            item {
                CustomButton(
                    "Заказать",
                    Modifier.fillMaxWidth()
                ) { viewModel.onEvent(MakingOrderEvent.MakeOrder) }
            }
        }
    }
}