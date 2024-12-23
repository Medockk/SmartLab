package com.example.smartlab.feature_app.presentation.AnalyzesCategory

sealed class AnalyzesCategoryEvent {

    data class FindText(val value: String): AnalyzesCategoryEvent()

    data class AnalyzesCatalogClick(val value: Boolean): AnalyzesCategoryEvent()
    data class AddClick(
        val name: String,
        val time: String,
        val price: String,
    ) : AnalyzesCategoryEvent()

    data object AddProcedureInCart : AnalyzesCategoryEvent()
    data object ChangeCardState : AnalyzesCategoryEvent()
}