/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.extensions.appendButton

sealed class AnnotationTextItem(var text: String) {
    data class Text(var label: String, var style: TextStyle = TextStyle.Default) : AnnotationTextItem(label)
    data class Button(var label: String, val underline: Boolean = false) : AnnotationTextItem(label)
}

@Composable
fun CheckBoxWithAction(
    checked: Boolean = false,
    labels: List<AnnotationTextItem> = listOf(),
    onCheckedChange: (Boolean) -> Unit = {},
    onTextClick: (Int) -> Unit = {}
) {
    val annotates = buildAnnotatedString {

        labels.forEachIndexed { index, data ->
            when (data) {
                is AnnotationTextItem.Button -> appendButton(
                    text = data.text,
                    textColor = MaterialTheme.colors.primary,
                    decoration = if (data.underline) TextDecoration.Underline
                    else TextDecoration.None,
                    tag = "text_$index"
                )

                is AnnotationTextItem.Text -> append(data.text)
            }
        }
    }
    Row(
        modifier = Modifier.padding(
            vertical = 4.dp,
            horizontal = 4.dp
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.primary,
                uncheckedColor = Color.Gray
            ),
            modifier = Modifier
                .size(16.dp)

        )

        Spacer(modifier = Modifier.width(8.dp))

        ClickableText(
            text = annotates,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSecondary
            ),
            onClick = { offset ->
                labels.forEachIndexed { index, _ ->
                    annotates.getStringAnnotations(
                        tag = "text_${index}",
                        start = offset,
                        end = offset
                    )
                        .firstOrNull()?.let {
                            onTextClick(index)
                        }
                }

            }
        )
    }
}

@Composable
fun IconWithAction(
    icon: (@Composable () -> Unit)? = null,
    labels: List<AnnotationTextItem> = listOf(),
    onTextClick: (Int) -> Unit = {}
) {
    val annotates = buildAnnotatedString {
        labels.forEachIndexed { index, data ->
            when (data) {
                is AnnotationTextItem.Button -> appendButton(
                    text = data.text,
                    textColor = MaterialTheme.colors.primary,
                    decoration = if (data.underline) TextDecoration.Underline
                    else TextDecoration.None,
                    tag = "text_$index"
                )

                is AnnotationTextItem.Text -> append(data.text)
            }
        }
    }
    Row(
        modifier = Modifier.padding(
            vertical = 4.dp,
            horizontal = 4.dp
        ),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {

        icon?.invoke()

        Spacer(modifier = Modifier.width(8.dp))

        ClickableText(
            text = annotates,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSecondary
            ),
            onClick = { offset ->
                labels.forEachIndexed { index, _ ->
                    annotates.getStringAnnotations(
                        tag = "text_${index}",
                        start = offset,
                        end = offset
                    )
                        .firstOrNull()?.let {
                            onTextClick(index)
                        }
                }

            }
        )
    }
}

@Composable
fun TextWithAction(
    labels: List<AnnotationTextItem> = listOf(),
    onTextClick: (Int) -> Unit = {},
) {
    val annotates = buildAnnotatedString {
        labels.forEachIndexed { index, data ->
            when (data) {
                is AnnotationTextItem.Button -> appendButton(
                    text = data.text,
                    textColor = MaterialTheme.colors.primary,
                    decoration = if (data.underline) TextDecoration.Underline
                    else TextDecoration.None,
                    tag = "text_$index"
                )

                is AnnotationTextItem.Text -> append(data.text)
            }
        }
    }
    ClickableText(
        text = annotates,
        style = MaterialTheme.typography.body1.copy(
            color = MaterialTheme.colors.onSecondary
        ),
        onClick = { offset ->
            labels.forEachIndexed { index, _ ->
                annotates.getStringAnnotations(
                    tag = "text_${index}",
                    start = offset,
                    end = offset
                )
                    .firstOrNull()?.let {
                        onTextClick(index)
                    }
            }

        }
    )

}

@Composable
fun TextAnnotationWithStyle(
    labels: List<AnnotationTextItem> = listOf(),
) {
    val annotates = buildAnnotatedString {
        labels.forEachIndexed { _, data ->
            when (data) {
                is AnnotationTextItem.Text -> {
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = data.style.color,
                        )
                    ) {
                        append(data.text)
                    }
                }
                else -> Unit
            }
        }
    }
    Text(
        text = annotates,
        style = MaterialTheme.typography.subtitle1,
    )
}

@Preview
@Composable
fun PreviewCheckboxInput() {
    BaseMainApp {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            CheckBoxWithAction(
                labels = listOf(
                    AnnotationTextItem.Text("Tes"),
                    AnnotationTextItem.Button("Ini Button"),
                    AnnotationTextItem.Text("Tes 1"),
                    AnnotationTextItem.Button("Ini Button 1")
                ),
                onTextClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            IconWithAction(
                icon = {
                    Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
                },
                labels = listOf(
                    AnnotationTextItem.Text("Tes"),
                    AnnotationTextItem.Button("Ini Button"),
                    AnnotationTextItem.Text("Tes 1"),
                    AnnotationTextItem.Button("Ini Button 1")
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextWithAction(
                labels = listOf(
                    AnnotationTextItem.Text("Tes"),
                    AnnotationTextItem.Button("Ini Button"),
                    AnnotationTextItem.Text("Tes 1"),
                    AnnotationTextItem.Button("Ini Button 1")
                )
            )
        }

    }
}