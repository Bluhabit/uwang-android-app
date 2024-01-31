/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.data.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking

inline fun <reified T> executeAsFlow(crossinline call: suspend () -> Response<T>): Flow<Response<T>> = flow {
    emit(Response.Loading)
    emit(call.invoke())
}.flowOn(Dispatchers.IO)

inline fun <reified T> executeThenReturn(crossinline call: suspend () -> Flow<Response<T>>): Response<T> {
    return runBlocking { call().last() }
}

