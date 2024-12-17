package com.example.smartlab.feature_app.presentation.AnalyzesCategory

sealed class AnalyzesCategoryEvent {

    data class FindText(val value: String): AnalyzesCategoryEvent()

    data class AnalyzesCatalogClick(val value: Boolean): AnalyzesCategoryEvent()
    data object AddClick : AnalyzesCategoryEvent()
}