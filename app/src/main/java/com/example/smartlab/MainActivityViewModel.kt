package com.example.smartlab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.OnBoard.OnBoardState
import com.example.smartlab.feature_app.domain.usecase.Queue.QueueUseCase
import kotlinx.coroutines.launch
import java.util.LinkedList

class MainActivityViewModel(
    private val useCase: QueueUseCase
): ViewModel() {

    init {
        checkRoute()
    }
    fun checkRoute(){
        viewModelScope.launch {
            val queue = useCase.getQueue()

            if (queue){

            }else{
                val list = listOf(
                    OnBoardItem(
                        image = R.drawable.botle,
                        title = "Анализы",
                        description = "Экспресс сбор и получение проб"
                    ),
                    OnBoardItem(
                        image = R.drawable.notification,
                        title = "Уведомления",
                        description = "Вы быстро узнаете о результатах"
                    ),
                    OnBoardItem(
                        image = R.drawable.monitoring,
                        title = "Мониторинг",
                        description = "Наши врачи всегда наблюдают \n" +
                                "за вашими показателями здоровья"
                    )
                )
                useCase.createQueue(LinkedList(list))
            }
        }
    }
}