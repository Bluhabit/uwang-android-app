/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.searchListTask

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.input.SearchBar
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray600

@Composable
fun SearchListPage(
    icon: (@Composable () -> Unit)? = null,
    message: String = String.Empty,
    search: SearchThisState = SearchThisState(),
    onSearchThis: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFFFFFFFF)),
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Arrow Back"
                )
            }
            SearchBar(
                placeholder = stringResource(id = R.string.text_placeholder_search_bar),
                value = search.word,
                onChange = onSearchThis,
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            icon?.invoke()
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.Top
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.body2,
                    lineHeight = 28.sp,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    color = Gray600,
                )
            }

        }
    }
}

@Composable
@Preview(widthDp = 500, heightDp = 750)
fun SearchListTask() {
    GaweanTheme() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            var search by remember {
                mutableStateOf(SearchThisState())
            }
            SearchListPage(
                search = search,
                onSearchThis = {
                    search = search.copy(word = it)
                },
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.searchlisttask),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                message = stringResource(id = R.string.text_placeholder_search_list_task),
            )
        }
    }
}