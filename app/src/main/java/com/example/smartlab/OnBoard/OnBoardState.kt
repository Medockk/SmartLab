package com.example.smartlab.OnBoard

data class OnBoardState(
    val page: OnBoardItem = OnBoardItem(0, "", ""),
    val pageCount: Int = 0,

)