package com.example.smartlab.OnBoard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.OnBoard.components.OnBoardIndicator
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.Lato60020_00B712
import com.example.smartlab.ui.theme.Lato60020_57A9FF
import com.example.smartlab.ui.theme.SF40014_939396
import com.example.smartlab.ui.theme._57A9FF33
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    val nav = rememberNavController()
    OnBoardScreen(nav)
}

@Composable
fun OnBoardScreen(
    navController: NavController,
    viewModel: OnBoardViewModel = koinViewModel()
) {
    val state = viewModel.state.value
    val paddingTop = LocalConfiguration.current.screenHeightDp / 30
    val paddingBottom = LocalConfiguration.current.screenHeightDp / 15

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete){
            navController.navigate(Route.SignInScreen.route){
                popUpTo(Route.OnBoardScreen.route){
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(top = paddingTop.dp,
                start = 20.dp)
    ){
        Row {
            Text(
                text = if (state.currentPage != 2) "Пропустить" else "Завершить",
                style = Lato60020_57A9FF,
                modifier = Modifier.clickable {
                    viewModel.onEvent(OnBoardEvent.NextPage)
                }
            )
            Spacer(Modifier.weight(1f))
            Box(modifier = Modifier.fillMaxWidth(0.6f)
                .fillMaxHeight(0.18f)
                .background(_57A9FF33, RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp)),
                contentAlignment = Alignment.Center
            ){
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = paddingBottom.dp),
        contentAlignment = Alignment.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight(0.15f)
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight(0.4f)
            ){
                Text(
                    text = state.page.title,
                    style = Lato60020_00B712
                )
                Text(
                    text = state.page.description,
                    style = SF40014_939396
                )
            }
            OnBoardIndicator(
                3,
                state.currentPage,
                Modifier.fillMaxWidth(0.15f)
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = paddingBottom.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Image(
            painter = painterResource(state.page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.6f)
        )
    }
}