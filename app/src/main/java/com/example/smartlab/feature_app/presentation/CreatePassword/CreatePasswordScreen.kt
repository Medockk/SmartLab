package com.example.smartlab.CreatePassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.smartlab.feature_app.presentation.CreatePassword.components.Indicator
import com.example.smartlab.feature_app.presentation.CreatePassword.components.NumberPad
import com.example.smartlab.ui.theme.SF40015_1A6FEE
import com.example.smartlab.ui.theme.SF40015_939396
import com.example.smartlab.ui.theme.SF70024Black

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreatePasswordScreen() {

    val paddingTop = LocalConfiguration.current.screenHeightDp / 14

    var currentIndicator by remember {
        mutableStateOf(-1)
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(top = paddingTop.dp,
                start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(Modifier.align(Alignment.End)){
            Text("Пропустить", style = SF40015_1A6FEE)
        }
        Column (
            modifier = Modifier.fillMaxHeight(0.07f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Создайте пароль", style = SF70024Black)
            Text("Для защиты ваших персональных данных",
                style = SF40015_939396)
        }
        Indicator(
            4, currentIndicator = currentIndicator,
            modifier = Modifier.fillMaxWidth(0.18f))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(0.8f)
                .fillMaxHeight(0.55f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item{
                LazyRow(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                        .fillParentMaxHeight(0.235f)
                ){
                    item {
                        NumberPad(1,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight()) {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                    item {
                        NumberPad(2,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight()) {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                    item {
                        NumberPad(3,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight())  {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                        .fillParentMaxHeight(0.235f)
                ){
                    item {
                        NumberPad(4,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight())  {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                    item {
                        NumberPad(5,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight()) {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                    item {
                        NumberPad(6,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight()) {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                        .fillParentMaxHeight(0.235f)
                ){
                    item {
                        NumberPad(7,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight()) {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                    item {
                        NumberPad(8,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight())  {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                    item {
                        NumberPad(9,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight()) {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                        .fillParentMaxHeight(0.235f)
                ){
                    item {
                        NumberPad(null,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight()) {
                            if (currentIndicator < 4){
                            currentIndicator++
                        }
                        }
                    }
                    item {
                        NumberPad(0,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight()) {
                            if (currentIndicator < 4){
                                currentIndicator++
                            }
                        }
                    }
                    item {
                        NumberPad(3,
                            isDelete = true,
                            modifier = Modifier.fillParentMaxWidth(0.31f)
                                .fillMaxHeight()) {
                            if (currentIndicator >= 0){
                                currentIndicator--
                            }
                        }
                    }
                }
            }
        }
        Spacer(Modifier)
    }
}