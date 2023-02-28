/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base.listener

interface BottomSheetListener {
    fun onContentClick(id: String, vararg params: String)
    fun onClose()
    fun onAction(id: String, vararg params: String)
}

class BottomSheetListenerImpl(
    private val onContentClick: (id: String, params: Array<out String>) -> Unit = { _, _ -> },
    private val onClose: () -> Unit = {},
    private val onActionClick: (id: String, params: Array<out String>) -> Unit = { _, _ -> }
) : BottomSheetListener {
    override fun onContentClick(id: String, vararg params: String) {
        this.onContentClick.invoke(id, params)
    }

    override fun onClose() {
        this.onClose.invoke()
    }

    override fun onAction(id: String, vararg params: String) {
        this.onActionClick.invoke(id, params)
    }

}

enum class BottomSheetType{
    BASIC,
    DASHBOARD
}