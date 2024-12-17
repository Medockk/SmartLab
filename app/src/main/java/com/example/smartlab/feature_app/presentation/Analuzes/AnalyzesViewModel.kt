package com.example.smartlab.feature_app.presentation.Analuzes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AnalyzesViewModel : ViewModel() {

    private val _state = mutableStateOf(AnalyzesState())
    val state: State<AnalyzesState> = _state

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