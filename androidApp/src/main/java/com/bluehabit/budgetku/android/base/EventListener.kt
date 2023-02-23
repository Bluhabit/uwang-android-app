package com.bluehabit.budgetku.android.base

import com.bluehabit.budgetku.android.base.listener.*

class EventListener {
    private var appEvent:AppStateEventListener?=null
    private var topAppBarListener:AppBarSelectedListener?=null
    private var bottomAppBarListener:BottomAppBarClickListener?=null
    private var snackBarClickListener:SnackBarClickListener?=null
    private var bottomSheetListener:BottomSheetListener?=null

    fun addOnEventListener(
        listener: AppStateEventListener
    ){
        appEvent = listener
    }
    fun sendEvent(eventName:String){
        appEvent?.onEvent(eventName)
    }

    fun addOnAppBarListener(
        listener: AppBarSelectedListener
    ){
        topAppBarListener = listener
    }

    fun topAppBarAction(id:String, params:Array<out String>){
        topAppBarListener?.onAction(id, *params)
    }
    fun topAppBarNavigation(){
        topAppBarListener?.onNavigation()
    }

    fun addOnBottomAppBarListener(
        listener: BottomAppBarClickListener
    ){
        bottomAppBarListener = listener
    }
    fun bottomAppBarClick(id:String,params: Array<out String>){
        bottomAppBarListener?.onItemClick(
            id,
            *params
        )
    }

    fun addSnackbarBarListener(
        listener: SnackBarClickListener
    ){
        snackBarClickListener = listener
    }

    fun snackbarContent(id:String, params:Array<out String>){
        snackBarClickListener?.onContent(id, *params)
    }
    fun snackbarAction(id:String){
        snackBarClickListener?.onAction(id)
    }

    fun addOnBottomSheetListener(listener: BottomSheetListener){
        bottomSheetListener = listener
    }

    fun bottomSheetAction(id:String,params: Array<out String>){
        bottomSheetListener?.onAction(id,*params)
    }

    fun bottomSheetContent(id: String,params: Array<out String>){
        bottomSheetListener?.onContentClick(
            id,
            *params
        )
    }
    fun bottomSheetClose(){
        bottomSheetListener?.onClose()
    }
}
