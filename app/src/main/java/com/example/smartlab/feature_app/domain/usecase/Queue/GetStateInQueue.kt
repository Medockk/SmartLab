package com.example.smartlab.feature_app.domain.usecase.Queue

import com.example.smartlab.feature_app.domain.manger.QueueManger

class GetStateInQueue(
    private val queueManger: QueueManger
) {

    suspend operator fun invoke() : Boolean{
        return queueManger.getStateInQueue()
    }
}