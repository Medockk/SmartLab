package com.example.smartlab.feature_app.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.repository.StorageRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.storage.storage
import java.io.ByteArrayOutputStream
import kotlin.time.Duration

class StorageRepositoryImpl: StorageRepository {

    override suspend fun getImageUrl(): String {
        val userID = client.auth.currentUserOrNull()?.id.toString()
        return client.storage.from(userID)
            .createSignedUrl(
                path = "myIcon.png",
                expiresIn = Duration.INFINITE
            )
    }

    override suspend fun setImage(image: ByteArray) {
        val userID = client.auth.currentUserOrNull()?.id.toString()
        val signedUploadUrl = client.storage.from(userID).createSignedUploadUrl(
            path = "myIcon.png",
            upsert = true
        )
        client.storage.from(userID).uploadToSignedUrl(
            path = "myIcon.png",
            token = signedUploadUrl.token,
            data = image
        )
    }

    override fun convertImageToByteArray(context: Context, image: Int): ByteArray {
        val bitmap = BitmapFactory.decodeResource(context.resources, image)
        val baos = ByteArrayOutputStream()
        bitmap.compress(
            Bitmap.CompressFormat.PNG,
            60,
            baos
        )
        return baos.toByteArray()
    }
}