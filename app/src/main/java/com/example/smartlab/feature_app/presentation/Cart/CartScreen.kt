package com.example.smartlab.feature_app.presentation.Cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import com.example.smartlab.R
import com.example.smartlab.core.presentation.CustomBackIcon
import com.example.smartlab.ui.theme.SF70024Black
import com.example.smartlab.ui.theme._B8C1CC

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prev() {
    CartScreen()
}

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel()
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
            modifier = Modifier.fillMaxHeight(0.09f)
        ){
            CustomBackIcon(
                modifier = Modifier.fillMaxHeight(0.4f)
                    .fillMaxWidth(0.09f)
            ) {
                viewModel.onEvent(CartEvent.BackClick)
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Корзина", style = SF70024Black)
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.delete),
                    contentDescription = "clear cart",
                    tint =  _B8C1CC
                )
            }
        }
    }
}