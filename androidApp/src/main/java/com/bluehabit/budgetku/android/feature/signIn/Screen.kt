/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signIn

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun ScreenSignIn(
    modifier: Modifier = Modifier,
    userID: String? = "empty",
    onSubmit: (email: String, password: String) -> Unit = { _, _ -> },
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    LazyColumn(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            TextField(value = email, onValueChange = {
                email = it
            })
            TextField(value = password, onValueChange = {
                password = it
            })
            Button(onClick = { onSubmit(email, password) }) {
                Text(text = "Click me")
            }
        }
        item {
            Text(text = userID ?: "emit")
        }
    }
}
