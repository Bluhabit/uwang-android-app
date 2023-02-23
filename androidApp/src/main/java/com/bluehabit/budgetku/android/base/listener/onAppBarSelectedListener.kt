package com.bluehabit.budgetku.android.base.listener

interface onAppBarSelectedListener {
    fun onNavigation()
    fun onAction(id:String,vararg params:String)
}