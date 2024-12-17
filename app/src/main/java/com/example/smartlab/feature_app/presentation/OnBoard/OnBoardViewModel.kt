package com.example.smartlab.OnBoard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.usecase.Queue.QueueUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardViewModel(
    private val useCase: QueueUseCase
): ViewModel() {
    private val _state = mutableStateOf(OnBoardState())
    val state: State<OnBoardState> = _state

    fun onEvent(event: OnBoardEvent){
        when (event){
            is OnBoardEvent.NextPage -> {
                viewModelScope.launch{
                    val page = useCase.getItemFromQueue()
                    if (page != null){
                        _state.value = state.value.copy(
                            page = page
                        )
                    }else{
                        _state.value = state.value.copy(
                            isComplete = true
                        )
                    }
                    _state.value = state.value.copy(
                        currentPage = if (state.value.currentPage != 2) {
                            state.value.currentPage + 1
                        } else {2}
                    )
                }
            }
        }
    }
}