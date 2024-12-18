package com.example.smartlab.feature_app.domain.usecase.Queue

import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.feature_app.domain.manger.QueueManger

class GetItemFromQueue(
    private val manger: QueueManger
) {

    suspend operator fun invoke(): OnBoardItem? {
        val queue = manger.getQueue()
        if (!queue.isNullOrEmpty()){
            val item = queue.pop()
            manger.saveQueue(queue)
            return item
        }
        return null
    }
}