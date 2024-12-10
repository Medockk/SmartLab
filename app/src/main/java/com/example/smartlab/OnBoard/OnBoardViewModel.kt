package com.example.smartlab.OnBoard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class OnBoardViewModel: ViewModel() {
    private val _state = mutableStateOf(OnBoardState())
    val state: State<OnBoardState> = _state


}