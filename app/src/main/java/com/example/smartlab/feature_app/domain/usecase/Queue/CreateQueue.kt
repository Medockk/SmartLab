package com.example.smartlab.feature_app.domain.usecase.Queue

import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.feature_app.domain.manger.QueueManger
import java.util.LinkedList

class CreateQueue(
    private val manger: QueueManger
) {

    suspend operator fun invoke(queue: LinkedList<OnBoardItem>){
        manger.saveQueue(queue)
    }
}