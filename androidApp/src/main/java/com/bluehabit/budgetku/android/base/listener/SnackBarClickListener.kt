package com.bluehabit.budgetku.android.base.listener

interface SnackBarClickListener {
    fun onContent(id:String,vararg params:String)
    fun onAction(id:String)
}