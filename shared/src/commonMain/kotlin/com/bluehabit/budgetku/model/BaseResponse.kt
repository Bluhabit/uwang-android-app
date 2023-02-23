package com.bluehabit.budgetku.model

data class BaseResponse<T>(
    val code: Int,
    val data: T,
    val message: String,
    val token: String,
)