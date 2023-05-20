/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey50
import com.bluehabit.budgetku.android.ui.Grey900

@Composable
fun FormInputSearch(
    value: String = "",
    placeholder: String = "",
    onChange: (String) -> Unit = {}
) {
    TextField(
        value = value,
        onValueChange = onChange,
        placeholder = {
            Text(text = placeholder)
        },
        maxLines = 1,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        enabled = true,
        textStyle = MaterialTheme.typography.subtitle2,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Grey900,
            backgroundColor = Grey50,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = ""
            )
        },
    )
}

@Preview
@Composable
fun PreviewFormInputSearch() {
    BaseMainApp {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                )
        ) {
            FormInputSearch(
                placeholder = "Cari disini"
            )
        }
    }
}