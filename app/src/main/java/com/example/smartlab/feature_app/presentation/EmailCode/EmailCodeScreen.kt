package com.example.smartlab.EmailCode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.core.presentation.CustomBackIcon
import com.example.smartlab.feature_app.presentation.EmailCode.components.EmailCodeTextField
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.SF40015_939396
import com.example.smartlab.ui.theme.SF60017Black

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    val nav = rememberNavController()
    EmailCodeScreen(nav)
}

@Composable
fun EmailCodeScreen(
    navController: NavController,
    viewModel: EmailCodeViewModel = viewModel()
) {

    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 15

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete){
            navController.navigate(Route.CreatePasswordScreen.route){
                popUpTo(Route.EmailCodeScreen.route){
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(top = paddingTop.dp,
                start = 20.dp),
        contentAlignment = Alignment.TopStart
    ){
        CustomBackIcon(
            Modifier.fillMaxWidth(0.1f)
                .fillMaxHeight(0.05f)
        ) {
            navController.navigate(Route.SignInScreen.route)
        }
    }

    val paddingBottom = LocalConfiguration.current.screenHeightDp / 15

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(start = 20.dp, end = 20.dp,
                bottom = paddingBottom.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.2f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Введите код из E-mail", style = SF60017Black)
            LazyRow(
                modifier = Modifier.fillMaxWidth(0.8f)
                    .fillMaxHeight(0.4f),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                val _state = state.code
                items(4){
                    EmailCodeTextField(
                        value = _state,
                        onValueChanged = {
                            viewModel.onEvent(EmailCodeEvent.EnteredCode(it))
                        },
                        modifier = Modifier.fillParentMaxWidth(0.2f)
                            .fillParentMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                if (state.timer.equals("0")){
                    Text(
                        "Отправить код заново",
                        style = SF40015_939396,
                        modifier = Modifier.clickable{
                            viewModel.onEvent(EmailCodeEvent.ResetTimer)
                        }
                    )
                }else{
                    Text("Отправить код повторно можно",
                        style = SF40015_939396)
                    Text(
                        text = "будет через ${state.timer} секунд",
                        style = SF40015_939396
                    )
                }
            }
        }
    }
}