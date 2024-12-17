package com.example.smartlab.feature_app.presentation.PatientCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.CustomButton
import com.example.smartlab.R
import com.example.smartlab.core.presentation.BottomNavigation
import com.example.smartlab.core.presentation.CustomTextField
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.SF40014_939396
import com.example.smartlab.ui.theme.SF70024Black
import com.example.smartlab.ui.theme._D9D9D980
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    val nav = rememberNavController()
    PatientCardScreen(nav)
}

@Composable
fun PatientCardScreen(
    navController: NavController,
    viewModel: PatientCardViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 20
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 14

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(top = paddingTop.dp, bottom = paddingBottom.dp)
    ) {
        Text("Карта пациента", style = SF70024Black)

        Box(
            modifier = Modifier.fillMaxWidth(0.4f)
                .fillMaxHeight(0.15f)
                .background(_D9D9D980, RoundedCornerShape(60.dp)),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(R.drawable.profile_default_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(0.3f)
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
                .fillMaxHeight(0.09f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Без карты пациента вы не сможете заказать анализы.",
                style = SF40014_939396)
            Text("В картах пациентов будут храниться результаты" +
                    " анализов вас и ваших близких.",
                style = SF40014_939396)
        }

        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.65f)
                .padding(horizontal = 20.dp)
        ) {
            item {
                CustomTextField(
                    value = state.name,
                    onValueChanged = {viewModel.onEvent(PatientCardOnEvent.EnteredName(it))},
                    hintText = "Name",
                    modifier = Modifier.fillParentMaxHeight(0.14f)
                )
            }
            item {
                CustomTextField(
                    value = state.surname,
                    onValueChanged = {viewModel.onEvent(PatientCardOnEvent.EnteredSurname(it))},
                    hintText = "Surname",
                    modifier = Modifier.fillParentMaxHeight(0.14f)
                )
            }
            item {
                CustomTextField(
                    value = state.patronymic,
                    onValueChanged = {viewModel.onEvent(PatientCardOnEvent.EnteredPatronymic(it))},
                    hintText = "Patronymic",
                    modifier = Modifier.fillParentMaxHeight(0.14f)
                )
            }
            item {
                CustomTextField(
                    value = state.birthdayData,
                    onValueChanged = {viewModel.onEvent(PatientCardOnEvent.EnteredBirthdayData(it))},
                    hintText = "Birthday data",
                    modifier = Modifier.fillParentMaxHeight(0.14f)
                )
            }
            item {
                CustomTextField(
                    value = state.gender,
                    onValueChanged = {viewModel.onEvent(PatientCardOnEvent.EnteredGender(it))},
                    hintText = "Gender",
                    isGender = true,
                    modifier = Modifier.fillParentMaxHeight(0.14f)
                )
            }
        }

        CustomButton(
            "Сохранить",
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(horizontal = 20.dp)
        ) { viewModel.onEvent(PatientCardOnEvent.SaveClick) }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        BottomNavigation(
            Modifier.fillMaxWidth(),
            analyzesClick = {
                navController.navigate(Route.AnalyzesScreen.route){
                    popUpTo(Route.PatientCardScreen.route){
                        inclusive = true
                    }
                }
            },
            {},{},{},
            selectedProfile = true
        )
    }
}