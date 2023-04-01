/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.common

sealed class Response<out R>{
    object Loading:Response<Nothing>()
    data class Result<out Result>(val data:Result):Response<Result>()
    data class Error(val message:String="",val stringRes:Int=0,val code:Int=0):Response<Nothing>()
}