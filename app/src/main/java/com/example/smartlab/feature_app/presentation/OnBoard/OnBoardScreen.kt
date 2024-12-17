package com.example.smartlab.OnBoard

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {

}

@Composable
fun OnBoardScreen(
    navController: NavController,
    viewModel: OnBoardViewModel = viewModel()
) {
    val state = viewModel.state.value
    val pager = rememberPagerState {
        state.countOfPage
    }

    HorizontalPager(
        state = pager,
        verticalAlignment = Alignment.Top
    ) {

    }
}