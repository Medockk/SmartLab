package com.example.smartlab.feature_app.presentation.Cart

import com.example.smartlab.feature_app.domain.model.Cart

data class CartState(
    val amount: String = "",
    val patientCount: Int = 1,
    val buttonEnabled: Boolean = false,

    val userCart: List<Cart> = emptyList(),
)
