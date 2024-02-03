/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileAction
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileState
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun CreateUsernameScreen(
    state: CompleteProfileState = CompleteProfileState(),
    onBackPressed: () -> Unit = {},
    onAction: (CompleteProfileAction) -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    val levelList = listOf(
        ItemLevel(
            image = R.drawable.image_level_1,
            title = stringResource(id = R.string.title_card_beginner),
            description = stringResource(id = R.string.description_card_beginner)
        ),
        ItemLevel(
            image = R.drawable.image_level_2,
            title = stringResource(id = R.string.title_card_intermediate),
            description = stringResource(id = R.string.description_card_Intermediate)
        ),
        ItemLevel(
            image = R.drawable.image_level_3,
            title = stringResource(id = R.string.title_card_expert),
            description = stringResource(id = R.string.description_card_expert)
        ),
    )
    var selectedIndex by remember {
        mutableStateOf<Int?>(null)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UwangColors.Base.White)
            .safeDrawingPadding(),
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = dimens.dp_16, end = dimens.dp_16,
                    top = dimens.dp_24, bottom = dimens.from(82.dp + 32.dp) // 82 (bottom nav size) + 32
                ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimens.dp_24)
                        .align(Alignment.Center)
                )

            }
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_24))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Bagaimana kami memanggilmu?",
                    style = UwangTypography.BodyXL.SemiBold,
                    color = UwangColors.Text.Main
                )
                Text(
                    text = "Buat nama @pengguna yang unik.",
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.Text.Secondary
                )
            }
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_16))
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Nama pengguna",
                placeholder = "Masukkan nama pengguna",
                value = state.usernameState,
                onValueChange = {
                    onAction(CompleteProfileAction.OnUsernameChange(it))
                },
                state = state.usernameAlertState,
            )
        }
    }
}