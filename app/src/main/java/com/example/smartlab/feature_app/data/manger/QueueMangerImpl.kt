
package com.example.smartlab.feature_app.data.manger

import android.content.Context
import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.feature_app.domain.manger.QueueManger
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.LinkedList

class QueueMangerImpl(
    context: Context
): QueueManger {

    private val key = "queue"
    val sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)

    override suspend fun getQueue(): LinkedList<OnBoardItem>? {
        val gson = sharedPreferences.getString(key, null)
        val type = object : TypeToken<LinkedList<OnBoardItem>>(){}.type
        return Gson().fromJson(gson, type)
    }

    override suspend fun saveQueue(queue: LinkedList<OnBoardItem>) {
        if (queue.isNotEmpty()){
            sharedPreferences.edit()
                .clear().putString(key, Gson().toJson(queue))
                .apply()
        }
    }

    override suspend fun saveStateInQueue(page: Int?) {
        if (page != null) {
            sharedPreferences.edit().putInt(key, page).apply()
        } else {
            sharedPreferences.edit().putBoolean(key, true).apply()
        }
    }
}
