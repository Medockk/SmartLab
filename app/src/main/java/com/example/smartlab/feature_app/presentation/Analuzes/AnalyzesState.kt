package com.example.smartlab.feature_app.presentation.Analuzes

import com.example.smartlab.feature_app.domain.model.CategoryData

data class AnalyzesState(
    val findText: String = "",
    val selectedCategory: Boolean = false,
    val addProcedure: Boolean = false,
    val isComplete: Boolean = false,

    val categoryList: List<CategoryData> = emptyList()
)
