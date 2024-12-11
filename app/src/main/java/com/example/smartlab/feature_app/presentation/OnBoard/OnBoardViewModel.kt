package com.example.smartlab.OnBoard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.smartlab.OnBoard.Components.OnBoardPage

class OnBoardViewModel: ViewModel() {
    private val _state = mutableStateOf(OnBoardState(
        page = OnBoardItem(0,"","")
    ))
    val state: State<OnBoardState> = _state

    fun onEvent(event: OnBoardEvent){
        when (event){
            is OnBoardEvent.NextPage -> {
                _state.value = state.value.copy(
                    page = OnBoardItem(0,"","")
                )
            }
        }
    }
}