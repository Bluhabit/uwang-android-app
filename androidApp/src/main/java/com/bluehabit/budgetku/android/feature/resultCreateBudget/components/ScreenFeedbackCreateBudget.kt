/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.resultCreateBudget.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.ButtonPrimary
import com.bluehabit.budgetku.android.ui.Grey300

@Composable
fun ScreenFeedbackCreateBudget(
    feedback: String = "",
    onChange: (String) -> Unit = {},
    onDismiss: () -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_overlay_result_create_budget),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(
                    fraction = 0.4f
                ),
            contentScale = ContentScale.FillWidth
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 30.dp,
                    end = 30.dp
                ),
            onClick = onDismiss
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = ""
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(
                    fraction = 0.8f
                )
                .align(
                    Alignment.Center
                )
                .padding(
                    horizontal = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(1.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.text_title_like_feedback),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 60.dp
                        ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                shape = CircleShape,
                                color = Grey300
                            )
                            .clickable(
                                enabled = true,
                                onClick = {}
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_thumb_up),
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                shape = CircleShape,
                                color = Grey300
                            )
                            .clickable(
                                enabled = true,
                                onClick = {}
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_thumb_down),
                            contentDescription = ""
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(
                    value = feedback,
                    onValueChange = onChange,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color(0xFFFAFAFA),
                        unfocusedBorderColor = Color(0xFFFAFAFA),
                        focusedBorderColor = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.onSurface,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.placeholder_input_feedback_create_budget),
                            style = MaterialTheme.typography.subtitle2,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    modifier = Modifier.height(100.dp)
                )
            }
            ButtonPrimary(text = stringResource(R.string.text_button_submit_feedback_create_budeget), onClick = onSubmit)
        }


    }


}

@Preview
@Composable
fun PreviewScreenFeedbackCreateBudget() {
    BaseMainApp {
        ScreenFeedbackCreateBudget()
    }
}