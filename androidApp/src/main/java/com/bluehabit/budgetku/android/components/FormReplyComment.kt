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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.ui.BudgetKuTheme
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey50
import com.bluehabit.budgetku.android.ui.Grey800

@Composable
fun FormReplyComment(
    value: String = "",
    isReply: Boolean = false,
    replyTo: String = "",
    onChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 18.dp,
                    topEnd = 18.dp
                )
            )
            .background(MaterialTheme.colors.surface)
            .shadow(
                elevation = 1.dp
            )
    ) {
        if(isReply){
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextAnnotationWithStyle(
                    labels = listOf(
                        AnnotationTextItem.Text(
                            "membalas"
                        ),
                        AnnotationTextItem.Text(
                            replyTo,
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colors.primary
                            ),
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = ""
            )
            OutlinedTextField(
                value = value,
                onValueChange = onChange,
                placeholder = {
                    Text(text = "Tulis komentar")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Grey800,
                    backgroundColor = Grey50
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "",
                tint = MaterialTheme.colors.primary
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun PreviewFormReplyComment() {
    BudgetKuTheme {
        Column {
            FormReplyComment()
        }
    }
}

@Preview
@Composable
fun PreviewFormReplyCommentTrue() {
    BudgetKuTheme {
        Column {
            FormReplyComment(
                isReply = true,
                replyTo = "@Trian Damai"
            )
        }
    }
}