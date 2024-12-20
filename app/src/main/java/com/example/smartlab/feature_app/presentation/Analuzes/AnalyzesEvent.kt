package com.example.smartlab.feature_app.presentation.Analuzes

sealed class AnalyzesEvent {

    data class EnterFindText(val value: String) : AnalyzesEvent()

    data class AnalyzesCatalogClick(val value: Boolean) : AnalyzesEvent()
    data class AnalyzesAddClick(
        val value: Boolean,
    ) : AnalyzesEvent()
    data class AnalyzesInformation(
        val title: String,
        val date: String,
        val price: String,
    ) : AnalyzesEvent()
    data object AnalyzesRemoveClick : AnalyzesEvent()
    data object CompleteChanges : AnalyzesEvent()
    data object AddProcedureInCart : AnalyzesEvent()
}