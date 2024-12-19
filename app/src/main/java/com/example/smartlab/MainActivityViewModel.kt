package com.example.smartlab

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.feature_app.domain.usecase.Auth.SignOutUseCase
import com.example.smartlab.feature_app.domain.usecase.Queue.QueueUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.LinkedList

class MainActivityViewModel(
    private val useCase: QueueUseCase,
    private val signOutUseCase: SignOutUseCase
): ViewModel() {

    init {
        viewModelScope.launch {
            try {
                signOut()
            } catch (e: Exception) {
                Log.e("mainInitEx", e.message.toString())
            }
        }
        checkRoute()
    }

    private suspend fun signOut(){
        signOutUseCase()
    }

    fun checkRoute(){
        viewModelScope.launch(Dispatchers.IO) {
            val queue = useCase.getQueue()
                val list = listOf(
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