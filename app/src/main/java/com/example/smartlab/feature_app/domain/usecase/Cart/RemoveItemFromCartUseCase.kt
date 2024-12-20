package com.example.smartlab.feature_app.domain.usecase.Cart

import com.example.smartlab.feature_app.domain.model.Cart
import com.example.smartlab.feature_app.domain.repository.CartRepository

class RemoveItemFromCartUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(cart: Cart) : Boolean{
        return cartRepository.removeItemFromCart(cart)
    }
}