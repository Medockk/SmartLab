package com.example.smartlab.feature_app.domain.usecase.Cart

import com.example.smartlab.feature_app.domain.model.Cart
import com.example.smartlab.feature_app.domain.repository.CartRepository

class GetAllUserItemFromCartUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke() : List<Cart> {
        return cartRepository.getAllUserItemFromCart()
    }
}