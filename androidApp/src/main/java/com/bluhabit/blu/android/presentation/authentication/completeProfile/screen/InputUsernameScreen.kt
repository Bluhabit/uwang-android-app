/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileAction
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileState
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun InputUsernameScreen(
    modifier: Modifier = Modifier,
    state: CompleteProfileState = CompleteProfileState(),
    onBackPressed:()->Unit,
    onAction: (CompleteProfileAction) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 18.dp, vertical = 18.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            IconButton(
                onClick = {
                    onBackPressed()
                }) {
                Icon(
                    painter = painterResource(id = com.bluehabit.core.ui.R.drawable.ic_arrow_back),
                    contentDescription = "arrow back"
                )
            }
            Text(
                text = stringResource(id = com.bluehabit.core.ui.R.string.edit_profile_screen),
                style = CustomTypography.Body.XL.W600,
                color = CustomColor.Neutral.Grey13
            )
            Text(
                text = stringResource(id = com.bluehabit.core.ui.R.string.edit_profile_screen_hint),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF626361)
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = com.bluhabit.blu.data.R.drawable.dummy_avatar),
                    contentDescription = null,
                    modifier = modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable {
                            // Handle image click
                        }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = com.bluehabit.core.ui.R.string.edit_profile_screen_placeholder),
                    style = CustomTypography.Body.Small.W400,
                    color = CustomColor.Neutral.Grey9
                )
                TextFieldPrimary(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = {
                        Text(
                            text = stringResource(id = com.bluehabit.core.ui.R.string.edit_profile_screen_field_label),
                            style = CustomTypography.Body.XS.W500
                        )
                    },
                    value = state.usernameState,
                    onValueChange = {
                        onAction(CompleteProfileAction.OnUsernameChange(it))
                    },
                    error = state.usernameError
                )
                Text(
                    text = state.usernameErrorText,
                    style = CustomTypography.Label.Small.W400,
                    color = CustomColor.Error.Red300
                )
            }
        }
        // Save Button
        ButtonPrimary(
            text = stringResource(id = com.bluehabit.core.ui.R.string.edit_profile_screen_next),
            modifier = Modifier
                .fillMaxWidth(),
            enabled = true,
            onClick = {
                onAction(CompleteProfileAction.SubmitData)
            }
        )
    }
}

@Preview
@Composable
fun EditProfileScreenPreview() {
    UwangTheme {
        InputUsernameScreen(
            onBackPressed = {},
            onAction = {}
        )
    }
}