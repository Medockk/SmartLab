package com.example.smartlab.feature_app.presentation.Analuzes

data class AnalyzesState(
    val findText: String = "",
    val procedureTitle: String = "",
    val selectedCategory: Boolean = false,
    val addProcedure: Boolean = false,
    val isComplete: Boolean = false,
)
