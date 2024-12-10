package com.example.smartlab.OnBoard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartlab.SplashScreen.SplashScreen



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardScreen(viewModel: OnBoardViewModel = viewModel()) {
    val state = viewModel.state.value
    val pager = rememberPagerState {
        state.countOfPage
    }

    HorizontalPager(
        state = pager
    ) {

    }
}