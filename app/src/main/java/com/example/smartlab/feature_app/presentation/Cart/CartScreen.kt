package com.example.smartlab.feature_app.presentation.Cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.CustomButton
import com.example.smartlab.R
import com.example.smartlab.core.presentation.CustomBackIcon
import com.example.smartlab.core.presentation.CustomCart
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.SF60020Black
import com.example.smartlab.ui.theme.SF70024Black
import com.example.smartlab.ui.theme._B8C1CC

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    val nav = rememberNavController()
    CartScreen(nav)
}

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = viewModel()
) {

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingTop.dp,
                start = 20.dp, end = 20.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight(0.09f)
            ){
                CustomBackIcon(
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth(0.09f)
                ) {
                    navController.navigate(Route.AnalyzesScreen.route){
                        popUpTo(Route.CartScreen.route){
                            inclusive = true
                        }
                    }
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text("Корзина", style = SF70024Black)
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.delete),
                        contentDescription = "clear cart",
                        tint =  _B8C1CC,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(CartEvent.ClearCart)
                        }
                    )
                }
            }

            val cartList = listOf(
                listOf("Клинический анализ крови с\n" +
                        "лейкоцитарной формулировкой", "690 ₽"),
                listOf("ПЦР-тест на определение\n" +
                        "РНК коронавируса стандартный", "1800 ₽")
            )
            LazyColumn(Modifier.padding(top = 30.dp)){
                items(cartList) { item ->
                    CustomCart(
                        item[0],
                        item[1],
                        {viewModel.onEvent(CartEvent.ClearCart)},
                        {viewModel.onEvent(CartEvent.AddItem)},
                        {viewModel.onEvent(CartEvent.RemoveItem)},
                        Modifier
                            .fillParentMaxHeight(0.18f)
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        Modifier.fillMaxHeight()
                    )
                }
                item {
                    Row {
                        Text("Сумма", style = SF60020Black)
                        Spacer(Modifier.weight(1f))
                        Text("${state.amount} ₽", style = SF60020Black)
                    }
                }
            }
        }

        val paddingBottom = LocalConfiguration.current.screenHeightDp / 20

        CustomButton(
            "Перейти к оформлению заказа",
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .padding(bottom = paddingBottom.dp)
        ) {
            navController.navigate(Route.MakingOrderScreen.route)
        }
    }
}