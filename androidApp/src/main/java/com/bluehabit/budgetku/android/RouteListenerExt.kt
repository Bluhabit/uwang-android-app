package com.bluehabit.budgetku.android

import android.content.Context
import android.content.res.Configuration

fun ApplicationState.listenChanges(
    ctx: Context,
    config: Configuration
) = this.router.addOnDestinationChangedListener { _, destination, _ ->
    currentRoute = destination.route.orEmpty()
}
