package com.example.smartlab

import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.feature_app.domain.manger.QueueManger
import java.util.LinkedList

class TestQueueMangerImpl: QueueManger {

    private var queue = LinkedList<OnBoardItem>()

    override suspend fun getQueue(): LinkedList<OnBoardItem>? {
        return queue
    }

    override suspend fun saveQueue(queue: LinkedList<OnBoardItem>) {
        this.queue = queue
    }
}