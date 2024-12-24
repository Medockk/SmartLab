package com.example.smartlab.SignIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.smartlab.navGraph.Route
import com.example.smartlab.R
import com.example.smartlab.core.presentation.CustomTextField
import com.example.smartlab.feature_app.presentation.SignIn.components.EnterWithYandexButton
import com.example.smartlab.ui.theme.SF40014_7E7E9A
import com.example.smartlab.ui.theme.SF40015Black
import com.example.smartlab.ui.theme.SF40015_939396
import com.example.smartlab.ui.theme.SF70024Black
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    val nav = rememberNavController()
    SignInScreen(nav)
}

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = koinViewModel()
) {

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 10
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 13

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete) {
            navController.navigate(Route.EmailCodeScreen.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingTop.dp, bottom = paddingBottom.dp,
                start = 20.dp, end = 20.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(0.48f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight(0.23f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.emojies),
                        contentDescription = "hello",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(32.dp)
                    )
                    Text("Добро пожаловать!", style = SF70024Black)
                }
                Text(
                    "Войдите, чтобы пользоваться функциями приложения",
                    style = SF40015Black
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight(0.27f)
            ) {
                Text("Вход по E-mail", style = SF40014_7E7E9A)
                CustomTextField(
                    value = state.email,
                    onValueChanged = {
                        viewModel.onEvent(SignInEvent.EnteredEmail(it))
                    },
                    hintText = "example@mail.ru",
                    modifier = Modifier.fillMaxHeight(0.9f)
                )
            }
            CustomButton(
                "Далее",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.23f)
            ) {
                viewModel.onEvent(SignInEvent.SendOTPClick)
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(0.25f)
        ) {
            Text("Или войдите с помощью", style = SF40015_939396)
            EnterWithYandexButton(
                Modifier.fillMaxHeight(0.8f)
            ) {
                viewModel.onEvent(SignInEvent.EnterWithYandex)
            }
        }
    }
}