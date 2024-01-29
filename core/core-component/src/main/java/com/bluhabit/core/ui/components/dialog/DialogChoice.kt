/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.dialog

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun DialogChoice(
    modifier:Modifier = Modifier,
    items: List<String> = listOf(),
    onSelected: (index: Int) -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Dialog(onDismissRequest = onDismissRequest) {
        LazyColumn(
            modifier = modifier
                .background(UwangColors.Base.White)
                .width(dimens.from(258.dp)),
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSelected(index)
                        }
                        .padding(dimens.dp_16)
                ) {
                    Text(
                        text = item,
                        style = UwangTypography.LabelSmall.Medium,
                        color = UwangColors.Text.Secondary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogChoicePreview() {
    val context = LocalContext.current
    val choiceList = listOf(
        "Take Photo",
        "Choose existing photo"
    )
    var showDialog by remember {
        mutableStateOf(false)
    }
    UwangTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            ButtonPrimary(
                text = "Show Dialog",
            ) {
                showDialog = true
            }
        }
        if (showDialog) {
            DialogChoice(
                items = choiceList,
                onSelected = {
                    Toast.makeText(context, "Selected index $it", Toast.LENGTH_SHORT).show()
                    showDialog = false
                },
                onDismissRequest = {
                    showDialog = false
                }
            )
        }
    }
}