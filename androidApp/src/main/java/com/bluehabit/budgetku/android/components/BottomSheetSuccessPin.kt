/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey700
import com.bluehabit.budgetku.android.ui.Grey900


@Composable
fun BottomSheetSuccessPin(
    onDismiss: () -> Unit = {},
) {
    BaseBottomSheet(
        textConfirmation = "Oke",
        enableConfirmation = true,
        showButtonConfirmation = true,
        showLineHeader = false,
        onDismiss = onDismiss,
        onConfirm = {},
        content = {
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.success_icon),
                    contentDescription = "",
                    modifier = Modifier.size(79.dp)
                )
                
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = stringResource(R.string.title_create_pin_success),
                    color = Grey900,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.subtitle_create_pin_success),
                    color = Grey700,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        )
                )
                
                Spacer(modifier = Modifier.height(40.dp))


            }
        }
    )    
}



@Preview
@Composable
fun PreviewBottomSheetSuccessPin() {
    BaseMainApp {
        BottomSheetSuccessPin()
    }
}