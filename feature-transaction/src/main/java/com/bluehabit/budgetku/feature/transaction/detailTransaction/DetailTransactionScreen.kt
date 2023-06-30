/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.transaction.detailTransaction

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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.extensions.formatToRupiah
import app.trian.mvi.ui.internal.UIContract
import com.bluehabit.budgetku.feature.transaction.detailTransaction.components.DetailTransactionBottomSheetType
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.DottedLine
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.routes.TransactionConstants
import com.bluehabit.core.ui.theme.Grey100
import com.bluehabit.core.ui.theme.Grey700

@Navigation(
    route = Routes.DetailTransaction.routeName,
    viewModel = DetailTransactionViewModel::class
)
@Composable
internal fun DetailTransactionScreen(
    uiContract: UIContract<DetailTransactionState,DetailTransactionIntent,DetailTransactionAction>
) = UIWrapper(uiContract) {

    LaunchedEffect(key1 = this, block = {
        dispatch(DetailTransactionAction.GetDetailTransaction)
    })

    BaseScreen(
        topAppBar = {
            TopAppBarDetailTransaction(
                onBackPressed = {
                    navigator.navigateUp()
                },
                onEdit = {
                    navigator.navigateSingleTop(Routes.EditTransaction.routeName)
                }
            )
        },
        bottomSheet = {
            when (state.bottomSheetType) {
                DetailTransactionBottomSheetType.SHARE -> {}
                DetailTransactionBottomSheetType.DELETE_CONFIRMATION -> {
                    BottomSheetConfirmation(
                        title = stringResource(R.string.text_title_delete_confirmation_transaction),
                        message = stringResource(R.string.text_message_delete_confirmation_transaction),
                        textConfirmation = stringResource(R.string.text_button_confirm_delete_transaction),
                        textCancel = stringResource(R.string.text_button_cancel_delete_transaction),
                        onDismiss = {
                            //hideBottomSheet()
                        },
                        onConfirm = {
                            //hideBottomSheet()
                            navigator.navigateUp()
                        }
                    )
                }
            }
        }
    ) {
        if (state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp)
                )
            }
        } else {
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
                        painter = painterResource(id = state.transactionIcon),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = state.transactionAmount.formatToRupiah(),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )

                Text(
                    text = state.transactionName,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Normal,
                    color = Grey700
                )
                Spacer(modifier = Modifier.height(24.dp))
                DottedLine()
                Spacer(modifier = Modifier.height(24.dp))
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
                        value = state.transactionAccountName,
                        image = state.transactionAccountIcon
                    )
                    ItemDetailTransaction(
                        label = stringResource(R.string.text_label_transaction_date_detail_transaction),
                        value = state.transactionDate
                    )
                    ItemDetailTransaction(
                        label = stringResource(R.string.text_label_transaction_category_detail_transaction),
                        value = state.transactionCategory
                    )
                    ItemDetailTransaction(
                        label = stringResource(R.string.text_label_transaction_type_detail_transaction),
                        value = state.transactionType
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
                        text = state.transactionAmount.formatToRupiah(),
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                DottedLine()
                Spacer(modifier = Modifier.height(24.dp))
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
                        onClick = {
                            commit {
                                copy(
                                    bottomSheetType = DetailTransactionBottomSheetType.DELETE_CONFIRMATION
                                )
                            }
                           // showBottomSheet()
                        },
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
                        .padding(
                            all = 6.dp
                        )
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize(),
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
fun TopAppBarDetailTransaction(
    onBackPressed: () -> Unit = {},
    onEdit: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackPressed) {
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
        IconButton(onClick = onEdit) {
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
    BaseMainApp() {
        DetailTransactionScreen(
              UIContract(
                controller = it,
                state = DetailTransactionState(),
            )
        )
    }
}