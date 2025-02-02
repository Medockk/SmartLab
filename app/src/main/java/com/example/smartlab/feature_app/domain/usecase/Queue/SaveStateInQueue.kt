package com.example.smartlab.feature_app.domain.usecase.Queue

import com.example.smartlab.feature_app.domain.manger.QueueManger

class SaveStateInQueue(
    private val queueManger: QueueManger
) {

    suspend operator fun invoke(isSkipped: Boolean){
        queueManger.saveStateInQueue(isSkipped)
    }
}