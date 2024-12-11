package com.example.smartlab.OnBoard

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartlab.OnBoard.Components.OnBoardPage
import com.example.smartlab.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    OnBoardPage(OnBoardItem(0, "werr","dfdwff"))
}

@Composable
fun OnBoardScreen(viewModel: OnBoardViewModel = viewModel()) {
    val state = viewModel.state.value
    val pager = rememberPagerState {
        state.countOfPage
    }

    HorizontalPager(
        state = pager,
        verticalAlignment = Alignment.Top
    ) {
        OnBoardPage(
            item = OnBoardItem(
                image = R.drawable.bottle,
                title = state.page.title,
                description = state.page.description
            )
        )
    }
}