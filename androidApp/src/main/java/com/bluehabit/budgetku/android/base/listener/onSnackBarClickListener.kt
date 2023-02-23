package com.bluehabit.budgetku.android.base.listener

interface onSnackBarClickListener {
    fun onContent(id:String,vararg params:String)
    fun onAction(id:String)
}