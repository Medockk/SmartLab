package com.example.smartlab.feature_app.domain.usecase.Cart

import com.example.smartlab.feature_app.domain.repository.CartRepository

class RemoveAllItemFromCartUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke() : Boolean{
        return cartRepository.removeAllItemFromCart()
    }
}