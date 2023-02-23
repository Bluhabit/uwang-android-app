package com.bluehabit.budgetku.android.base.listener

interface AppBarSelectedListener {
    fun onNavigation()
    fun onAction(id:String,vararg params:String)
}