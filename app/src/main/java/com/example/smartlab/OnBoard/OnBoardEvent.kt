package com.example.smartlab.OnBoard

sealed class OnBoardEvent {
    data object NextPage: OnBoardEvent()
}