/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray500


@Composable
fun SearchBar(
    value: String = String.Empty,
    placeholder: String = String.Empty,
    onChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(

        value = value,
        onValueChange = onChange,
        placeholder = {
            Text(
                modifier = Modifier
                    .width(228.dp)
                    .height(20.dp),
                text = placeholder,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "",
                tint = Gray500,
                modifier = Modifier.size(20.dp)
            )
        },
        modifier = Modifier
            .width(310.dp)
            .height(51.dp)
    )
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewSearchBar() {
    GaweanTheme() {
        Column(
            modifier = Modifier.padding(
                horizontal = 14.dp,
                vertical = 10.dp
            )
        ) {
            var search by remember {
                mutableStateOf("")
            }
            SearchBar(
                value = search,
                placeholder = stringResource(id = R.string.text_placeholder_search_bar),
                onChange = { search = it },
            )
        }
    }
}