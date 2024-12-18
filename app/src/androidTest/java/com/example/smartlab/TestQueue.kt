package com.example.smartlab

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.OnBoard.OnBoardScreen
import com.example.smartlab.OnBoard.OnBoardViewModel
import com.example.smartlab.feature_app.domain.usecase.Queue.AddItemInQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.ClearQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.CreateQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.GetItemFromQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.GetQueue
import com.example.smartlab.feature_app.domain.usecase.Queue.QueueUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestQueue {

    private lateinit var viewModel: OnBoardViewModel
    private lateinit var useCase: QueueUseCase

    @get:Rule
    private val rule
    = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp(){
        val manger = TestQueueMangerImpl()
        useCase = QueueUseCase(
            addItemInQueue = AddItemInQueue(manger),
            getItemFromQueue = GetItemFromQueue(manger),
            getQueue = GetQueue(manger),
            createQueue = CreateQueue(manger),
            clearQueue = ClearQueue(manger)
        )
        viewModel = OnBoardViewModel(useCase)
        Log.v("client", "init complete")
    }

    @Test
    fun getItem(){
        runBlocking {
            useCase.clearQueue()
            useCase.addItemInQueue(
                OnBoardItem(R.drawable.botle, "title1", "description1")
            )
            useCase.addItemInQueue(
                OnBoardItem(R.drawable.notification, "title2", "description2")
            )
        }
        rule.setContent {
            OnBoardScreen(
                navController = rememberNavController(),
                viewModel = viewModel
            )
        }
        rule.onNodeWithText("title1").assertExists()
        rule.onNodeWithText("Start").performClick()
        rule.onNodeWithText("title2").assertExists()
    }
}