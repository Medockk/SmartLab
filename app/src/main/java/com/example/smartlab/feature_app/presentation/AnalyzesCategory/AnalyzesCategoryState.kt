package com.example.smartlab.feature_app.presentation.AnalyzesCategory

import com.example.smartlab.feature_app.domain.model.CategoryData
import com.example.smartlab.feature_app.domain.model.ListOfProcedures

data class AnalyzesCategoryState(
    val findText: String = "",
    val selectedCategory: Boolean = false,

    val categoryList: List<CategoryData> = emptyList(),
    val proceduresList: List<ListOfProcedures> = emptyList(),

    val amount: String = "",

    val procedureName: String = "",
    val procedureDate: String = "",
    val procedurePrice: String = "",

    val isAdded: Boolean = false,
    val isRemoved: Boolean = false,

    val inCart: Boolean = false,
)
