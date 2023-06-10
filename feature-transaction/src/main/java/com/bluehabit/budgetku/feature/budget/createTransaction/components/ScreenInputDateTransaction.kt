/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createTransaction.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluehabit.core.ui.components.button.ButtonPrimary
import java.time.LocalDate

@Composable
fun ScreenInputDateTransaction(
    date: LocalDate? = null,
    onSelectDate: () -> Unit = {},
    onAddMore: () -> Unit = {},
    onSave: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val buttonWidth = (currentWidth / 2) - 20.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.text_title_input_date_transaction_create_transaction),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_header_input_date_transaction),
                contentDescription = "",
                modifier = Modifier.size(260.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .clickable(
                        enabled = true,
                        onClick = onSelectDate
                    )
                    .background(MaterialTheme.colors.surface)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pin),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = date?.toString().orEmpty()
                            .ifEmpty { stringResource(R.string.text_placeholder_input_date_transaction_create_transaction) })
                }
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = ""
                )

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topEnd = 20.dp,
                        topStart = 20.dp
                    )
                )
                .background(MaterialTheme.colors.surface)
                .padding(
                    vertical = 20.dp,
                    horizontal = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonPrimary(
                text = stringResource(R.string.text_button_add_more_transaction_create_transaction),
                fullWidth = false,
                modifier = Modifier.width(buttonWidth),
                onClick = onAddMore
            )
            Spacer(modifier = Modifier.width(8.dp))
            ButtonOutlinedPrimary(
                text = stringResource(R.string.text_button_save_transaction_create_transaction),
                fullWidth = false,
                modifier = Modifier.width(buttonWidth),
                onClick = onSave
            )
        }
    }

}

@Preview
@Composable
fun PreviewScreenInputDateTransaction() {
    BaseMainApp {
        ScreenInputDateTransaction()
    }
}