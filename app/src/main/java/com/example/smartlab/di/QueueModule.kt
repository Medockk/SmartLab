package com.example.smartlab.di

import com.example.smartlab.feature_app.data.manger.QueueMangerImpl
import com.example.smartlab.feature_app.domain.manger.QueueManger
import com.example.smartlab.feature_app.domain.usecase.Queue.AddItemInQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.ClearQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.CreateQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.GetItemFromQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.GetQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.QueueUseCase
import org.koin.dsl.module

val moduleQueue = module {

    single<QueueManger> {
        QueueMangerImpl(get())
    }
    factory<QueueUseCase> {
        QueueUseCase(
            AddItemInQueue(get()),
            GetItemFromQueue(get()),
            GetQueue(get()),
            CreateQueue(get()),
            ClearQueue(get())
        )
    }
}