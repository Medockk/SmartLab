package com.example.smartlab.EmailCode

data class EmailCodeState(
    val timer: String = "60",
    val isComplete: Boolean = false,

    val code1: String = "",
    val code2: String = "",
    val code3: String = "",
    val code4: String = "",
    val code5: String = "",
    val code6: String = "",

    val allCode: String = "",
)
