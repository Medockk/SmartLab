package com.example.smartlab.OnBoard

data class OnBoardState(
    val page: OnBoardItem = OnBoardItem(0, "", ""),
    val countOfPage: Int = 0,

)