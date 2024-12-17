package com.example.smartlab.OnBoard

import com.example.smartlab.R

data class OnBoardState(
    val page: OnBoardItem = OnBoardItem(R.drawable.botle, "Анализы", "Экспресс сбор и получение проб"),
    val countOfPage: Int = 2,
    val isComplete: Boolean = false,
    val currentPage: Int = 0,
)