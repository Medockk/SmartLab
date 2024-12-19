package com.example.smartlab.feature_app.presentation.Analuzes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.model.CategoryData
import com.example.smartlab.feature_app.domain.usecase.Category.GetAllCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnalyzesViewModel(
    private val getAllCategoryUseCase: GetAllCategoryUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AnalyzesState())
    val state: State<AnalyzesState> = _state

    init {
        viewModelScope.launch {
            try {
                getAllCategory()
            } catch (e: Exception) {
                Log.e("categoryEx", e.message.toString())
            }
        }
    }

    private suspend fun getAllCategory(){
        try {
            val list = getAllCategoryUseCase()

            withContext(Dispatchers.IO){
                _state.value = state.value.copy(
                    categoryList = list
                )
            }
        } catch (e: Exception) {
            Log.e("getAllCategoryEx", e.message.toString())
        }
    }

    fun onEvent(event: AnalyzesEvent){
        when (event){
            is AnalyzesEvent.EnterFindText -> {
                _state.value = state.value.copy(
                    findText = event.value
                )
            }

            is AnalyzesEvent.AnalyzesAddClick -> {
                _state.value = state.value.copy(
                    addProcedure = event.value
                )
            }
            is AnalyzesEvent.AnalyzesCatalogClick -> {
                _state.value = state.value.copy(
                    selectedCategory = event.value,
                    isComplete = true
                )
            }
            AnalyzesEvent.AnalyzesRemoveClick -> {

            }

            AnalyzesEvent.CompleteChanges -> {
                _state.value = state.value.copy(
                    isComplete = !state.value.isComplete
                )
            }
        }
    }
}