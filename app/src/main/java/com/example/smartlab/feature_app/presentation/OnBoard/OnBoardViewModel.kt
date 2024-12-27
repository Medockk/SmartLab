package com.example.smartlab.OnBoard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.usecase.Queue.QueueUseCase
import kotlinx.coroutines.launch

class OnBoardViewModel(
    private val useCase: QueueUseCase
): ViewModel() {

    private val _state = mutableStateOf(OnBoardState())
    val state: State<OnBoardState> = _state

    init {
        viewModelScope.launch {
           _state.value = state.value.copy(
               isComplete = useCase.getStateInQueue.invoke()
           )
            Log.v("m", state.value.isComplete.toString())
        }
    }

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

                    useCase.saveStateInQueue(true)
                    Log.v("true","true")
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