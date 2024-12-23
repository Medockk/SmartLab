package com.example.smartlab.feature_app.presentation.Payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.CustomButton
import com.example.smartlab.R
import com.example.smartlab.core.presentation.CustomEmptyButton
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.SF40014_1A6FEE
import com.example.smartlab.ui.theme.SF40014_939396
import com.example.smartlab.ui.theme.SF40016_939396
import com.example.smartlab.ui.theme.SF60020Black
import com.example.smartlab.ui.theme.SF60020_00B712
import com.example.smartlab.ui.theme._1A6FEE

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    PaymentScreen(navController = rememberNavController())
}

@Composable
fun PaymentScreen(
    navController: NavController,
    viewModel: PaymentViewModel = viewModel()
) {
    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15

    LaunchedEffect(key1 = !state.isLoading) {
        if (state.isLoading){
            viewModel.onEvent(PaymentEvent.NextPaymentState)
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(top = paddingTop.dp),
        contentAlignment = Alignment.Center
    ){
        Text("Оплата", style = SF60020Black)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(fraction = if (!state.isComplete) 0.2f else 0.5f)
                .padding(bottom = paddingTop.dp, start = 20.dp, end = 20.dp)
        ){
            if (!state.isComplete){
                Image(
                    painter = painterResource(R.drawable.loading),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth(0.2f)
                        .rotate(state.rotate)
                )
                Text(state.text, style = SF40016_939396)
            }else{
                Image(
                    painter = painterResource(R.drawable.botle),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth(0.6f)
                )
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight(0.7f)
                ){
                    Text("Ваш заказ успешно оплачен!",
                        style = SF60020_00B712)
                    Text("Вам осталось дождаться приезда медсестры и сдать анализы. До скорой встречи!",
                        style = SF40014_939396, textAlign = TextAlign.Center)
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text("Не забудьте ознакомиться с", style = SF40014_939396)
                            Row (
                                modifier = Modifier
                                    .clickable {

                                    }
                            ){
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.rules),
                                    contentDescription = "rules",
                                    tint = _1A6FEE,
                                    modifier = Modifier.padding(horizontal = 5.dp)
                                )
                                Text("правилами", style = SF40014_1A6FEE)
                            }
                        }
                        Text("подготовки к сдаче анализов", style = SF40014_1A6FEE)
                    }
                }
            }
        }
    }

    if (state.isComplete){
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            val paddingBottom = LocalConfiguration.current.screenHeightDp / 30
            Column (
                Modifier
                    .fillMaxHeight(0.15f)
                    .padding(start = 20.dp, end = 20.dp,
                        bottom = paddingBottom.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                CustomEmptyButton(
                    Modifier.fillMaxWidth()
                        .fillMaxHeight(0.4f),
                    "Чек покупки"
                ) {

                }
                CustomButton(
                    "На главную",
                    Modifier.fillMaxWidth()
                        .fillMaxHeight(0.8f)
                ) {
                    navController.navigate(Route.AnalyzesScreen.route){
                        popUpTo(Route.PaymentScreen.route){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}