package com.example.smartlab.OnBoard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.OnBoard.components.OnBoardPage
import com.example.smartlab.R
import com.example.smartlab.navGraph.Route
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
    val pagerState = rememberPagerState { state.countOfPage }

    LaunchedEffect(key1 = !state.isComplete) {
        if (state.isComplete) {
            navController.navigate(Route.SignInScreen.route) {
                popUpTo(Route.OnBoardScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
    ) { page ->
        if (page == 0){
            viewModel.currentPage(page)
            OnBoardPage(
                item = state.page,
                index = page,
                indicatorCount = state.countOfPage
            ) {
                navController.navigate(Route.SignInScreen.route)
                viewModel.onEvent(OnBoardEvent.SkipClick)
            }
        }

        if (page == 1){
            viewModel.currentPage(page)
            OnBoardPage(
                item = OnBoardItem(
                    R.drawable.notification,
                    title = "Уведомления",
                    description = "Вы быстро узнаете о результатах"
                ),
                index = page,
                indicatorCount = state.countOfPage
            ) {
                navController.navigate(Route.SignInScreen.route)
                viewModel.onEvent(OnBoardEvent.SkipClick)
            }
        }
        if (page == 2){
            viewModel.currentPage(page)
            OnBoardPage(
                item = OnBoardItem(
                    R.drawable.monitoring,
                    "Мониторинг",
                    "Наши врачи всегда наблюдают \n" +
                            "за вашими показателями здоровья"
                ),
                index = page,
                indicatorCount = state.countOfPage
            ) {
                navController.navigate(Route.SignInScreen.route)
            }
        }
    }
}