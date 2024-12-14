package com.example.smartlab.CreateCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.CustomButton
import com.example.smartlab.core.presentation.CustomTextField
import com.example.smartlab.ui.theme.SF40014_939396
import com.example.smartlab.ui.theme.SF40015_1A6FEE
import com.example.smartlab.ui.theme.SF70024Black

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateCardScreen(
    viewModel: CreateCardViewModel = viewModel()
){

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 14
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 7

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingTop.dp, bottom = paddingBottom.dp,
                start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (
            modifier = Modifier.fillMaxHeight(0.2f),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ){
                Text("Создание карты\nпациента", style = SF70024Black)
                Text("Пропустить", style = SF40015_1A6FEE)
            }
            Text("Без карты пациента вы не сможете заказать анализы.",
                style = SF40014_939396)
            Text("В картах пациентов будут храниться результаты анализов вас и ваших близких.",
                style = SF40014_939396)
        }

        LazyColumn (
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.75f)
        ){
            item{
                CustomTextField(
                    value = state.name,
                    onValueChanged = {
                        viewModel.onEvent(CreateCardEvent.EnteredName(it))
                    },
                    hintText = "Имя",
                    modifier = Modifier.fillMaxWidth()
                        .fillParentMaxHeight(0.15f)
                )
            }
            item{
                CustomTextField(
                    value = state.patronymic,
                    onValueChanged = {
                        viewModel.onEvent(CreateCardEvent.EnteredPatronymic(it))
                    },
                    hintText = "Отчество",
                    modifier = Modifier.fillMaxWidth()
                        .fillParentMaxHeight(0.15f)
                )
            }
            item{
                CustomTextField(
                    value = state.surname,
                    onValueChanged = {
                        viewModel.onEvent(CreateCardEvent.EnteredSurname(it))
                    },
                    hintText = "Фамилия",
                    modifier = Modifier.fillMaxWidth()
                        .fillParentMaxHeight(0.15f)
                )
            }
            item{
                CustomTextField(
                    value = state.birthdayData,
                    onValueChanged = {
                        viewModel.onEvent(CreateCardEvent.EnteredBirthdayData(it))
                    },
                    hintText = "Дата рождения",
                    modifier = Modifier.fillMaxWidth()
                        .fillParentMaxHeight(0.15f)
                )
            }
            item{
                CustomTextField(
                    value = state.gender,
                    onValueChanged = {
                        viewModel.onEvent(CreateCardEvent.EnteredGender(it))
                    },
                    isGender = true,
                    hintText = "Пол",
                    modifier = Modifier.fillMaxWidth()
                        .fillParentMaxHeight(0.15f)
                )
            }
        }
        CustomButton(
            title = "Создать",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.375f)
        ) {

        }
    }
}