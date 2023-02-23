package com.bluehabit.budgetku.android.base.listener

interface onBottomSheetListener {
    fun onContentClick(id:String, vararg params:String)
    fun onClose()
    fun onAction(id:String,vararg params: String)
}