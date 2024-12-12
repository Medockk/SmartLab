package com.example.smartlab.feature_app.domain.manger

import com.example.smartlab.OnBoard.OnBoardItem
import java.util.LinkedList

interface QueueManger {

    suspend fun getQueue() : LinkedList<OnBoardItem>?

    suspend fun saveQueue(queue: LinkedList<OnBoardItem>)
}