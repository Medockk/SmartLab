package com.example.smartlab.feature_app.presentation.Analuzes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.smartlab.feature_app.domain.usecase.Category.GetAllCategoryUseCase
import com.example.smartlab.feature_app.domain.usecase.Procedure.GetAllProcedure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnalyzesViewModel(
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val getAllProcedureUseCase: GetAllProcedure
) : ViewModel() {

    private val _state = mutableStateOf(AnalyzesState())
    val state: State<AnalyzesState> = _state

    init {
        viewModelScope.launch {
            try {
                launch { getAllCategory() }
                launch { getAllProcedure() }
            } catch (e: Exception) {
                Log.e("initEx", e.message.toString())
            }
        }
    }

    private suspend fun getAllProcedure(){
        val list = getAllProcedureUseCase()

        withContext(Dispatchers.IO){
            _state.value = state.value.copy(
                procedureList = list
            )
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

            is AnalyzesEvent.AnalyzesInformation -> {
                _state.value = state.value.copy(
                    dateProcedure = event.date,
                    priceProcedure = event.price,
                    nameProcedure = event.title
                )
            }
        }
    }
}