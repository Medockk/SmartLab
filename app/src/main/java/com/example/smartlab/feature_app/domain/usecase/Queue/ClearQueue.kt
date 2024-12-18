package com.example.smartlab.feature_app.domain.usecase.Queue

import com.example.smartlab.feature_app.domain.manger.QueueManger
import java.util.LinkedList

class ClearQueue(
    private val manger: QueueManger
) {

    suspend operator fun invoke(){
        manger.saveQueue(LinkedList())
    }
}