package com.example.smartlab.feature_app.presentation.Analuzes

import com.example.smartlab.feature_app.domain.model.Cart
import com.example.smartlab.feature_app.domain.model.CategoryData
import com.example.smartlab.feature_app.domain.model.ListOfProcedures

data class AnalyzesState(
    val findText: String = "",
    val selectedCategory: Boolean = false,
    val addProcedure: Boolean = false,
    val isComplete: Boolean = false,

    val isRemoved: Boolean = false,

    val nameProcedure: String = "",
    val dateProcedure: String = "",
    val priceProcedure: String = "",

    val categoryList: List<CategoryData> = emptyList(),
    val procedureList: List<ListOfProcedures> = emptyList(),
    val userCart: List<Cart> = emptyList(),
)
