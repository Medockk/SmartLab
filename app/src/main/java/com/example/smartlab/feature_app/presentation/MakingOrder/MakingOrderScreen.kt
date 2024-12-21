package com.example.smartlab.feature_app.presentation.MakingOrder

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.CustomButton
import com.example.smartlab.R
import com.example.smartlab.core.presentation.CustomBackIcon
import com.example.smartlab.core.presentation.CustomEmptyButton
import com.example.smartlab.core.presentation.CustomTextField
import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.feature_app.presentation.MakingOrder.components.CustomMakingOrderMenu
import com.example.smartlab.feature_app.presentation.MakingOrder.components.CustomTextFieldButton
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.SF40014_939396
import com.example.smartlab.ui.theme.SF50015Black
import com.example.smartlab.ui.theme.SF50015_939396
import com.example.smartlab.ui.theme.SF50017Black
import com.example.smartlab.ui.theme.SF70024Black
import com.example.smartlab.ui.theme._B8C1CC
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    val nav = rememberNavController()
    MakingOrderScreen(nav)
}

@Composable
fun MakingOrderScreen(
    navController: NavController,
    viewModel: MakingOrderViewModel = koinViewModel()
) {
    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete){
            navController.navigate(Route.PaymentScreen.route){
                popUpTo(Route.MakingOrderScreen.route){
                    inclusive = true
                }
            }
        }
    }

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
                navController.navigate(Route.CartScreen.route){
                    popUpTo(Route.MakingOrderScreen.route){
                        inclusive = true
                    }
                }
            }
            Text("Оформление заказа", style = SF70024Black)
        }

        LazyColumn (
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
                .padding(top = 30.dp)
        ){
            item {
                Column {
                    Text("Адрес *", style = SF40014_939396)
                    CustomTextFieldButton(
                        title = "Введите ваш адрес",
                        boxModifier = Modifier.fillParentMaxHeight(0.07f),
                        buttonModifier = Modifier.fillMaxWidth().fillMaxHeight()
                    ) {
                        viewModel.onEvent(MakingOrderEvent.AddressClick)
                    }
                }
            }
            item {
                Column {
                    Text("Дата и время*", style = SF40014_939396)
                    CustomTextFieldButton(
                        title = "Выберите дату и время",
                        boxModifier = Modifier.fillParentMaxHeight(0.07f),
                        buttonModifier = Modifier.fillMaxWidth().fillMaxHeight()
                    ){
                        viewModel.onEvent(MakingOrderEvent.DateClick)
                    }
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillParentMaxHeight(0.2f)
                ) {
                    Text("Кто будет сдавать анализы? *", style = SF40014_939396)
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = if (state.gender == UserData.male){
                                    painterResource(R.drawable.gender_male)
                                }else{
                                    painterResource(R.drawable.gender_female)
                                },
                                contentDescription = "gender",
                                modifier = Modifier.size(24.dp)
                            )
                            CustomTextField(
                                value = state.person,
                                onValueChanged = {
                                    viewModel.onEvent(MakingOrderEvent.EnteredPerson(it))
                                },
                                hintText = "Your surname and name",
                                modifier = Modifier.fillParentMaxHeight(0.07f)
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                    CustomEmptyButton(
                        Modifier.fillParentMaxWidth(),
                        "Добавить еще пациента"
                    ) {
                        viewModel.onEvent(MakingOrderEvent.AddOnMorePatient)
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
                        singleLine = false,
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
                    Text("${state.proceduresCount} анализ",
                        style = SF50017Black)
                    Spacer(Modifier.weight(1f))
                    Text("${state.price} ₽",
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
    if (state.addressClick){
        val addressHeight = LocalConfiguration.current.screenHeightDp / 4
        CustomMakingOrderMenu(
            height = addressHeight.dp,
            icon = ImageVector.vectorResource(R.drawable.map),
            isAddress = true,
            title = "Адрес сдачи анализов",
            value = state.address,
            onValueChanged = {viewModel.onEvent(MakingOrderEvent.EnteredAddress(it))},
            selectClick = {}
        ) {
            viewModel.onEvent(MakingOrderEvent.AddressClick)
        }
    }
    if (state.dateClick){
        val dateHeight = LocalConfiguration.current.screenHeightDp / 2
        CustomMakingOrderMenu(
            height = dateHeight.dp,
            icon = ImageVector.vectorResource(R.drawable.cross),
            isDate = true,
            title = "Дата и время",
            value = state.data,
            onValueChanged = {viewModel.onEvent(MakingOrderEvent.EnteredData(it))},
            selectClick = {}
        ) {
            viewModel.onEvent(MakingOrderEvent.DateClick)
        }
    }
    if (state.personClick){
        val personHeight = LocalConfiguration.current.screenHeightDp / 2
        CustomMakingOrderMenu(
            height = personHeight.dp,
            icon = ImageVector.vectorResource(R.drawable.cross),
            isDate = true,
            title = "Выбор пациента",
            value = state.person,
            onValueChanged = {viewModel.onEvent(MakingOrderEvent.EnteredPerson(it))},
            userData = UserData(
                name = state.person,
                surname = "", patronymic = "", birthdayData = "", gender = state.gender
            ),
            selectClick = {}
        ) {
            viewModel.onEvent(MakingOrderEvent.AddOnMorePatient)
        }
    }
}