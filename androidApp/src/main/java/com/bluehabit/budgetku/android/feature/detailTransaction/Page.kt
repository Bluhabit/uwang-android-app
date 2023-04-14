/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.detailTransaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.formatToRupiah
import com.bluehabit.budgetku.android.components.BottomSheetConfirmation
import com.bluehabit.budgetku.android.components.ButtonOutlinedPrimary
import com.bluehabit.budgetku.android.components.DottedLine
import com.bluehabit.budgetku.android.ui.Grey100
import com.bluehabit.budgetku.android.ui.Grey700

object DetailTransaction {
    const val routeName = "DetailTransaction"
}

fun NavGraphBuilder.routeDetailTransaction(
    state: ApplicationState,
) {
    composable(DetailTransaction.routeName) {
        ScreenDetailTransaction(appState = state)
    }
}

@Composable
internal fun ScreenDetailTransaction(
    appState: ApplicationState,
) = UIWrapper<DetailTransactionViewModel>(appState = appState) {

    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()

    with(appState) {
        setupTopAppBar {

        }

        setupBottomSheet {
            when (state.bottomSheetType) {
                DetailTransactionBottomSheetType.SHARE -> {}
                DetailTransactionBottomSheetType.DELETE_CONFIRMATION -> {
                    BottomSheetConfirmation(
                        title = stringResource(R.string.text_title_delete_confirmation_transaction),
                        message = stringResource(R.string.text_message_delete_confirmation_transaction),
                        textConfirmation = stringResource(R.string.text_button_confirm_delete_transaction),
                        textCancel = stringResource(R.string.text_button_cancel_delete_transaction)
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                rememberScrollState(),
                orientation = Orientation.Vertical
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Grey100)
                .padding(
                    all = 8.dp
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_dummy_detail_transaction),
                contentDescription = "",
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = dataState.transactionAmount.formatToRupiah(),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )

        Text(
            text = dataState.transactionName,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Normal,
            color = Grey700
        )
        Spacer(modifier = Modifier.height(16.dp))
        DottedLine()
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                )
        ) {
            Text(
                text = stringResource(R.string.text_title_label_detail_transaction),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            ItemDetailTransaction(
                label = stringResource(R.string.text_label_account_source_detail_transaction),
                value = dataState.transactionAccountName,
                image = R.drawable.ic_jago
            )
            ItemDetailTransaction(
                label = stringResource(R.string.text_label_transaction_date_detail_transaction),
                value = dataState.transactionDate.toString()
            )
            ItemDetailTransaction(
                label = stringResource(R.string.text_label_transaction_category_detail_transaction),
                value = dataState.transactionCategory
            )
            ItemDetailTransaction(
                label = stringResource(R.string.text_label_transaction_type_detail_transaction),
                value = dataState.transactionType
            )
        }
        DottedLine()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 16.dp,
                    horizontal = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.text_label_total_amount_detail_transaction),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = dataState.transactionAmount.formatToRupiah(),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
        }
        DottedLine()
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ButtonOutlinedPrimary(text = stringResource(R.string.text_button_share_transaction_detail_transaction))
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.clip(MaterialTheme.shapes.large)
            ) {
                Text(
                    text = stringResource(R.string.text_button_detele_transaction_detail_transaction),
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.error
                )
            }
        }

    }
}

@Composable
fun ItemDetailTransaction(
    label: String = "",
    value: String = "",
    image: Int? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Normal,
            color = Grey700
        )
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (image != null) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                    )
                }
            }
            Text(
                text = value,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface
            )
        }

    }
}

@Composable
fun TopAppBarDetailTransaction() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_long_left),
                contentDescription = ""
            )
        }
        Text(
            text = stringResource(R.string.text_title_detail_transaction),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = ""
            )
        }
    }

}

@Preview
@Composable
fun PreviewScreenDetailTransaction() {
    BaseMainApp(
        topAppBar = {
            TopAppBarDetailTransaction()
        }
    ) {
        ScreenDetailTransaction(it)
    }
}