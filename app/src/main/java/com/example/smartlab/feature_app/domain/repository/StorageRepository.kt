package com.example.smartlab.feature_app.domain.repository

import android.content.Context

interface StorageRepository {

    suspend fun getImageUrl() : String
    suspend fun setImage(image: ByteArray)

    fun convertImageToByteArray(context: Context, image: Int) : ByteArray
}