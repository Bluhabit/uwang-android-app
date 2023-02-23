package com.bluehabit.budgetku.utils

sealed class Response<out R>{
    object Loading:Response<Nothing>()
    data class Result<out Result>(val data:Result):Response<Result>()
    data class Error(val message:String="",val code:Int=0):Response<Nothing>()
}
