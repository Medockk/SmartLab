package com.example.smartlab.feature_app.domain.usecase.Queue

data class QueueUseCase(
    val addItemInQueue: AddItemInQueue,
    val getItemFromQueue: GetItemFromQueue,
    val getQueue: GetQueue,
    val createQueue: CreateQueue,
    val clearQueue: ClearQueue,
    val saveQueue: SaveQueue,
    val saveStateInQueue: SaveStateInQueue,
    val getStateInQueue: GetStateInQueue,
)
