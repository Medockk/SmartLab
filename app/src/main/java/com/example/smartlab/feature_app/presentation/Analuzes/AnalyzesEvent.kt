package com.example.smartlab.feature_app.presentation.Analuzes

sealed class AnalyzesEvent {

    data class EnterFindText(val value: String) : AnalyzesEvent()

    data object ResultsClick : AnalyzesEvent()
    data object SupportsClick : AnalyzesEvent()
    data object ProfileClick : AnalyzesEvent()
    data class AnalyzesCatalogClick(val value: Boolean) : AnalyzesEvent()
    data class AnalyzesAddClick(val value: Boolean) : AnalyzesEvent()
    data object AnalyzesRemoveClick : AnalyzesEvent()
}