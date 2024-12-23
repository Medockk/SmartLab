package com.example.smartlab.feature_app.presentation.Cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    val nav = rememberNavController()
    CartScreen(nav)
}

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 20

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingTop.dp, bottom = paddingBottom.dp,
                start = 20.dp, end = 20.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight(0.09f)
            ) {
                CustomBackIcon(
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth(0.09f)
                ) {
                    navController.navigate(Route.AnalyzesScreen.route) {
                        popUpTo(Route.CartScreen.route) {
                            inclusive = true
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Корзина", style = SF70024Black)
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.delete),
                        contentDescription = "clear cart",
                        tint = _B8C1CC,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(CartEvent.ClearCart)
                        }
                    )
                }
            }
            LazyColumn(
                Modifier
                    .fillMaxHeight(0.9f)
                    .padding(top = 30.dp)
            ) {
                items(state.userCart) { item ->
                    CustomCart(
                        title = item.procedure,
                        price = item.price + " ₽",
                        patientCount = state.patientCount,
                        onPatientCountChanged = {
                            viewModel.onEvent(CartEvent.ChangedPatientCount(it))
                        },
                        deleteProcedureClick = {
                            viewModel.onEvent(CartEvent.DeleteItem(
                                item.procedure,
                                item.price
                            ))
                        },
                        Modifier
                            .fillParentMaxHeight(0.18f)
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        Modifier.fillMaxHeight()
                    )
                }
                item {
                    if (state.amount.isNotEmpty()){
                        Row {
                            Text("Сумма", style = SF60020Black)
                            Spacer(Modifier.weight(1f))
                            Text("${state.amount} ₽", style = SF60020Black)
                        }
                    }
                }
            }
        }
        CustomButton(
            title = "Перейти к оформлению заказа",
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            enabled = state.buttonEnabled
        ) {
            navController.navigate(Route.MakingOrderScreen.route)
        }
    }
}