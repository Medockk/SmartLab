package com.example.smartlab.CreatePassword

data class CreatePasswordState(
    val password: String = "",
    val isComplete: Boolean = false,
    val isLogged: Boolean = false,
)
