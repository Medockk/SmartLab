package com.example.smartlab.feature_app.domain.usecase.Queue

import com.example.smartlab.feature_app.domain.manger.QueueManger

class GetQueue(
    private val manger: QueueManger
) {

    suspend operator fun invoke():Boolean{
        val queue = manger.getQueue()
        return queue != null
    }
}