/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base.listener

interface AppBarListener {
    fun onNavigationButtonClicked()
    fun onNavigationItemClicked(id: String, vararg params: String)
    fun onFabClicked(id: String, vararg params: String)
    fun onActionItemClicked(id: String, vararg params: String)

}

class AppBarListenerImpl(
    private val onNavButtonClicked: () -> Unit = {},
    private val onNavItemClicked: (id: String, params: Array<out String>) -> Unit = { _, _ -> },
    private val onFabClicked: (id: String, params: Array<out String>) -> Unit = { _, _ -> },
    private val onActionClicked: (id: String, params: Array<out String>) -> Unit = { _, _ -> }
) : AppBarListener {
    override fun onNavigationButtonClicked() {
        this.onNavButtonClicked.invoke()
    }

    override fun onNavigationItemClicked(id: String, vararg params: String) {
        this.onNavItemClicked.invoke(id, params)
    }

    override fun onFabClicked(id: String, vararg params: String) {
        this.onFabClicked.invoke(id, params)
    }

    override fun onActionItemClicked(id: String, vararg params: String) {
        this.onActionClicked.invoke(id, params)
    }

}

enum class TopAppBarType{
    HIDE,
    BASIC,
    DASHBOARD
}

enum class BottomAppBarType{
    HIDE,
    BASIC,
    DASHBOARD
}