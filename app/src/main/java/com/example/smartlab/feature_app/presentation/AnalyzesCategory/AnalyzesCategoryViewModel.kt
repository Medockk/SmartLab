package com.example.smartlab.feature_app.presentation.AnalyzesCategory

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AnalyzesCategoryViewModel: ViewModel() {

    private val _state = mutableStateOf(AnalyzesCategoryState())
    val state: State<AnalyzesCategoryState> = _state

    fun onEvent(event: AnalyzesCategoryEvent){
        when (event){
            is AnalyzesCategoryEvent.FindText -> TODO()

            AnalyzesCategoryEvent.ProfileClick -> TODO()
            AnalyzesCategoryEvent.ResultsClick -> TODO()
            AnalyzesCategoryEvent.SupportsClick -> TODO()
            is AnalyzesCategoryEvent.AnalyzesCatalogClick -> {
                _state.value = state.value.copy(
                    selectedCategory = event.value
                )
            }
            AnalyzesCategoryEvent.AddClick -> {

            }
        }
    }
}