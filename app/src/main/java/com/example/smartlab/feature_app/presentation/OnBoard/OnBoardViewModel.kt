package com.example.smartlab.OnBoard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.usecase.Queue.QueueUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.LinkedList

class OnBoardViewModel(
    private val useCase: QueueUseCase
): ViewModel() {

    private val _state = mutableStateOf(OnBoardState())
    val state: State<OnBoardState> = _state

    fun onEvent(event: OnBoardEvent){
        when (event){
            is OnBoardEvent.SkipClick -> {
                viewModelScope.launch{
//                    val page = useCase.getItemFromQueue()
//
//                    if (state.value.currentPage == 2){
//                        _state.value = state.value.copy(
//                            isComplete = true,
//                            page = OnBoardItem(0, "","")
//                        )
//                    }
//
//                    if (page != null){
//                        _state.value = state.value.copy(
//                            page = page
//                        )
//                    }
//
//
//
//                    _state.value = state.value.copy(
//                        currentPage = if (state.value.currentPage != 3) {
//                            state.value.currentPage + 1
//                        } else {3}
//                    )

                    useCase.saveStateInQueue(state.value.currentPage)
                }
            }
        }
    }

    fun currentPage(page: Int){
        _state.value = state.value.copy(
            currentPage = page
        )
    }
}