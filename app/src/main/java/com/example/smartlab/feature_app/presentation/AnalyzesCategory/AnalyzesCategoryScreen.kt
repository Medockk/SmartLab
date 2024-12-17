package com.example.smartlab.feature_app.presentation.AnalyzesCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.core.presentation.AnalyzesCatalog
import com.example.smartlab.core.presentation.AnalyzesFindTextField
import com.example.smartlab.core.presentation.BottomNavigation
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.SF50015White
import com.example.smartlab.ui.theme.SF50015_7E7E9A
import com.example.smartlab.ui.theme._1A6FEE
import com.example.smartlab.ui.theme._F5F5F9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    val nav = rememberNavController()
    AnalyzesCategoryScreen(nav)
}

@Composable
fun AnalyzesCategoryScreen(
    navController: NavController,
    viewModel: AnalyzesCategoryViewModel = viewModel()
) {
    val state = viewModel.state.value
    val verticalPadding = LocalConfiguration.current.screenHeightDp / 15

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = verticalPadding.dp, bottom = verticalPadding.dp,
                start = 20.dp, end = 20.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnalyzesFindTextField(
            value = state.findText,
            onValueChanged = { viewModel.onEvent(AnalyzesCategoryEvent.FindText(it)) },
            hintText = "Искать анализы",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.07f)
        )

        val categoryList = listOf(
            "Популярные", "Covid", "Комплексные", "Чекапы",
            "Биохимия", "Гормоны", "Иммунитет", "Витамины",
            "Аллергены", "Анализ крови", "Анализ мочи",
            "Анализ кала", "Только в клинике"
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.07f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(categoryList) { text ->
                val selectedCategory = remember { mutableStateOf(false) }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .padding(end = 16.dp)
                        .background(
                            if (selectedCategory.value) _1A6FEE else _F5F5F9,
                            RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            selectedCategory.value = !selectedCategory.value
                            viewModel.onEvent(AnalyzesCategoryEvent.AnalyzesCatalogClick(!selectedCategory.value))
                        }
                ) {
                    Text(
                        text, style = if (selectedCategory.value) SF50015White else SF50015_7E7E9A,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AnalyzesCatalog(
                    modifier = Modifier.fillParentMaxWidth()
                        .fillParentMaxHeight(0.25f)
                        .padding(bottom = 16.dp),
                    title = "ПЦР-тест на определение РНК\nкоронавируса стандартный",
                    data = "2 дня",
                    price = "1800 ₽"
                ){
                    viewModel.onEvent(AnalyzesCategoryEvent.AddClick)
                }
            }
            item {
                AnalyzesCatalog(
                    modifier = Modifier.fillParentMaxWidth()
                        .fillParentMaxHeight(0.25f)
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
                        .fillParentMaxHeight(0.23f)
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
                        .fillParentMaxHeight(0.23f)
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
                        .fillParentMaxHeight(0.23f)
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
                        .fillParentMaxHeight(0.23f)
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
                        .fillParentMaxHeight(0.23f)
                        .padding(bottom = 16.dp),
                    title = "Группа крови + Резус-фактор",
                    data = "1 день",
                    price = "750 ₽"
                ){

                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomNavigation(
            Modifier.fillMaxWidth(),
            analyzesClick = {
                navController.navigate(Route.AnalyzesScreen.route){
                    popUpTo(Route.AnalyzesCategoryScreen.route){
                        inclusive = true
                    }
                }
            },
            {},{},
            profileClick = {
                navController.navigate(Route.PatientCardScreen.route){
                    popUpTo(Route.AnalyzesCategoryScreen.route){
                        inclusive = true
                    }
                }
            },
            selectedAnalyzes = true
        )
    }
}