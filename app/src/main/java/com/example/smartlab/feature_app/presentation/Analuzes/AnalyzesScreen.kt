package com.example.smartlab.feature_app.presentation.Analuzes

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.core.presentation.AnalyzesCatalog
import com.example.smartlab.core.presentation.AnalyzesFindTextField
import com.example.smartlab.core.presentation.BottomNavigation
import com.example.smartlab.core.presentation.MoreInformationAboutProcedure
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.SF40014White
import com.example.smartlab.ui.theme.SF50015White
import com.example.smartlab.ui.theme.SF50015_7E7E9A
import com.example.smartlab.ui.theme.SF60017_939396
import com.example.smartlab.ui.theme.SF80020White
import com.example.smartlab.ui.theme._1A6FEE
import com.example.smartlab.ui.theme._76B3FF
import com.example.smartlab.ui.theme._92E9D4
import com.example.smartlab.ui.theme._97D9F0
import com.example.smartlab.ui.theme._CDE3FF
import com.example.smartlab.ui.theme._F5F5F9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    val nav = rememberNavController()
    AnalyzesScreen(nav)
}

@Composable
fun AnalyzesScreen(
    navController: NavController,
    viewModel: AnalyzesViewModel = viewModel()
) {

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete){
            viewModel.onEvent(AnalyzesEvent.CompleteChanges)
            navController.navigate(Route.AnalyzesCategoryScreen.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingTop.dp,
                start = 20.dp, end = 20.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AnalyzesFindTextField(
            value = state.findText,
            onValueChanged = {
                viewModel.onEvent(AnalyzesEvent.EnterFindText(it))
            },
            hintText = "Искать анализы",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.06f)
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
        ) {
            Text("Акции и новости", style = SF60017_939396)
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    val brush = Brush.linearGradient(
                        listOf(_76B3FF, _CDE3FF)
                    )
                    Box(
                        Modifier
                            .fillParentMaxWidth(0.8f)
                            .fillParentMaxHeight()
                            .background(brush, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Box {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(16.dp)
                            ) {
                                Text("Чек-ап для\nмужчин", style = SF80020White)
                                Column(
                                    verticalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxHeight(0.65f)
                                ) {
                                    Text("9 исследований", style = SF40014White)
                                    Text("8000 ₽ ", style = SF80020White)
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterEnd)
                                .offset(20.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.man),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                item {
                    val brush = Brush.linearGradient(
                        listOf(_97D9F0, _92E9D4)
                    )
                    Box(
                        Modifier
                            .fillParentMaxWidth(0.8f)
                            .fillParentMaxHeight()
                            .padding(horizontal = 16.dp)
                            .background(brush, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Box {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(16.dp)
                            ) {
                                Text("Подготовка к\nвакцинации", style = SF80020White)
                                
                                Text(
                                    "Комплексное обследование\n" +
                                            "перед вакцинацией", style = SF40014White
                                )
                                Text("4000 ₽ ", style = SF80020White)

                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterEnd)
                                .offset(60.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.covid),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }

        val categoryList = listOf(
            "Популярные", "Covid", "Комплексные", "Чекапы",
            "Биохимия", "Гормоны", "Иммунитет", "Витамины",
            "Аллергены", "Анализ крови", "Анализ мочи",
            "Анализ кала", "Только в клинике"
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.13f)
        ){
            Text("Каталог анализов", style = SF60017_939396)
            LazyRow (
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                items(categoryList) {text ->
                    val selectedCategory = remember { mutableStateOf(false) }
                    Box (
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillParentMaxHeight()
                            .padding(end = 16.dp)
                            .background(if (selectedCategory.value) _1A6FEE else _F5F5F9, RoundedCornerShape(10.dp))
                            .clickable {
                                selectedCategory.value = !selectedCategory.value
                                viewModel.onEvent(AnalyzesEvent.AnalyzesCatalogClick(selectedCategory.value))
                            }
                    ){
                        Text(text, style = if (selectedCategory.value) SF50015White else SF50015_7E7E9A,
                            modifier = Modifier.padding(horizontal = 20.dp))
                    }
                }
            }
        }

        val paddingBottom = LocalConfiguration.current.screenHeightDp / 15
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(bottom = paddingBottom.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AnalyzesCatalog(
                    modifier = Modifier.fillParentMaxWidth()
                        .padding(bottom = 16.dp),
                    title = "ПЦР-тест на определение РНК\nкоронавируса стандартный",
                    data = "2 дня",
                    price = "1800 ₽"
                ){
                    viewModel.onEvent(AnalyzesEvent.AnalyzesAddClick(true))
                }
            }
            item {
                AnalyzesCatalog(
                    modifier = Modifier.fillParentMaxWidth()
                        .padding(bottom = 16.dp),
                    title = "Клинический анализ крови с лейкоцитарной формулировкой",
                    data = "1 день",
                    price = "690 ₽"
                ){

                }
            }
            item {
                AnalyzesCatalog(
                    modifier = Modifier.fillParentMaxWidth()
                        .padding(bottom = 16.dp),
                    title = "Биохимический анализ крови, базовый",
                    data = "1 день",
                    price = "2440 ₽"
                ){

                }
            }
            item {
                AnalyzesCatalog(
                    modifier = Modifier.fillParentMaxWidth()
                        .padding(bottom = 16.dp),
                    title = "СОЭ (венозная кровь)",
                    data = "1 день",
                    price = "240 ₽"
                ){

                }
            }
            item {
                AnalyzesCatalog(
                    modifier = Modifier.fillParentMaxWidth()
                        .padding(bottom = 16.dp),
                    title = "Общий анализ мочи",
                    data = "1 день",
                    price = "350 ₽"
                ){

                }
            }
            item {
                AnalyzesCatalog(
                    modifier = Modifier.fillParentMaxWidth()
                        .padding(bottom = 16.dp),
                    title = "Тироксин свободный (Т4 свободный)",
                    data = "1 день",
                    price = "680 ₽"
                ){

                }
            }
            item {
                AnalyzesCatalog(
                    modifier = Modifier.fillParentMaxWidth()
                        .padding(bottom = 16.dp),
                    title = "Группа крови + Резус-фактор",
                    data = "1 день",
                    price = "750 ₽"
                ){

                }
            }
        }
    }

    if (!state.addProcedure){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            BottomNavigation(
                Modifier.fillMaxWidth()
                    .background(Color.White),
                {},{},{},
                profileClick = {navController.navigate(Route.PatientCardScreen.route){
                    popUpTo(Route.AnalyzesScreen.route){
                        inclusive = true
                    }
                } },
                selectedAnalyzes = true
            )
        }
    }

    if (state.addProcedure){
        val paddingBottom = (LocalConfiguration.current.screenHeightDp / 2.3).toInt()

        val startOffset = (LocalConfiguration.current.screenHeightDp)
        val endOffset = LocalConfiguration.current.screenWidthDp - paddingBottom

        var boxState by remember { mutableStateOf(startOffset) }

        val offset by animateDpAsState(
            targetValue = boxState.dp,
            animationSpec = tween(1500)
        )

        boxState = endOffset
        MoreInformationAboutProcedure(
            closeClick = {
                viewModel.onEvent(AnalyzesEvent.AnalyzesAddClick(false))
            }, addClick = {}, modifier = Modifier
                .padding(bottom = paddingTop.dp)
                .offset(y = offset),
            title = "Клинический анализ крови\nс лейкоцитарной формулой",
            price = "Добавить за 690 ₽")
    }
}