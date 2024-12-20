package com.example.smartlab.feature_app.domain.repository

import com.example.smartlab.feature_app.domain.model.Cart

interface CartRepository {

    suspend fun addItemInCart(procedure: Cart) : Boolean
    suspend fun removeItemFromCart(procedure: Cart) : Boolean

    suspend fun getAllUserItemFromCart() : List<Cart>
}