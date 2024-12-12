package com.example.smartlab.feature_app.domain.usecase.Queue

import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.feature_app.domain.manger.QueueManger

class AddItemInQueue(
    private val manger: QueueManger
) {

    suspend operator fun invoke(item: OnBoardItem){
        val queue = manger.getQueue()
        if (queue != null){
            queue.add(item)
            manger.saveQueue(queue)
        }
    }
}