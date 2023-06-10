/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.core.ui.viewModel.BaseViewModel

inline fun <reified VM : BaseViewModel<*, *>> NavGraphBuilder.pageWrapper(
    route: String,
    controller: UIController,
    parent: String? = null,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    crossinline content: @Composable VM.() -> Unit = {}
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks
    ) {
        val vm = (if (parent.isNullOrEmpty()) {
            hiltViewModel()
        } else {
            val parentEntry = remember(controller.router.currentBackStackEntry) {
                controller
                    .router
                    .getBackStackEntry(parent)
            }
            hiltViewModel<VM>(parentEntry)
        }).also { it.setController(controller) }
        content(vm)
    }
}

@Composable
inline fun <reified VM : BaseViewModel<*, *>> PageWrapper(
    appState: UIController,
    parent: String? = null,
    content: @Composable VM.() -> Unit = {}
) {
    val vm = (if (parent.isNullOrEmpty()) {
        hiltViewModel()
    } else {
        val parentEntry = remember(appState.router.currentBackStackEntry) {
            appState
                .router
                .getBackStackEntry(parent)
        }
        hiltViewModel<VM>(parentEntry)
    }).also { it.setController(appState) }
    content(vm)
}