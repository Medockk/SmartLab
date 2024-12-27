
package com.example.smartlab.feature_app.data.manger

import android.content.Context
import android.util.Log
import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.feature_app.domain.manger.QueueManger
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.LinkedList

class QueueMangerImpl(
    context: Context
): QueueManger {

    private val key = "queue"
    private val stateKey = "stateKey"
    val sharedPreferencesState = context.getSharedPreferences(stateKey, Context.MODE_PRIVATE)
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

    override suspend fun saveStateInQueue(isSkipped: Boolean) {
        sharedPreferencesState.edit().clear().putBoolean(stateKey, isSkipped).apply()
        Log.e("save", isSkipped.toString())
    }

    override suspend fun getStateInQueue(): Boolean {
        Log.e("get", sharedPreferencesState.getBoolean(stateKey, false).toString())
        val stateQueue = sharedPreferencesState.getBoolean(stateKey, false)
        return stateQueue
    }
}
