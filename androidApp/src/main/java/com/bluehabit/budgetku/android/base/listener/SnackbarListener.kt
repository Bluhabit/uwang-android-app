/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base.listener

interface SnackbarListener {
    fun onContent(id:String,vararg params:String)
    fun onAction(id:String)
}

class SnackbarListenerImpl(
    private val onContentClicked:(id:String,params:Array<out String>)->Unit={_,_->},
    private val onActionClicked:(id:String)->Unit={}
):SnackbarListener{
    override fun onContent(id: String, vararg params: String) {
        this.onContentClicked.invoke(id,params)
    }

    override fun onAction(id: String) {
        this.onActionClicked.invoke(id)
    }
}