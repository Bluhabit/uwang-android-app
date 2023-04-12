/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createTransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.feature.createTransaction.components.ScreenInputTransactionNameAndCategory
import com.bluehabit.budgetku.android.ui.Blue800

object CreateTransaction {
    const val routeName = "CreateTransaction"
}

fun NavGraphBuilder.routeCreateTransaction(
    state: ApplicationState,
) {
    composable(CreateTransaction.routeName) {
        ScreenCreateTransaction(appState = state)
    }
}

@Composable
internal fun ScreenCreateTransaction(
    appState: ApplicationState,
) = UIWrapper<CreateTransactionViewModel>(appState = appState) {
    with(appState) {
        hideTopAppBar()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue800)
    ) {


//        ScreenInputAmount()
//        ScreenInputTransactionType()
//        ScreenInputAccount()
        ScreenInputTransactionNameAndCategory()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "",
                tint = MaterialTheme.colors.onPrimary
            )
            LinearProgressIndicator(
                progress = 0.5f,
                modifier = Modifier
                    .fillMaxWidth(
                        fraction = 0.7f
                    )
                    .height(8.dp),
                color = MaterialTheme.colors.onPrimary,
                backgroundColor = MaterialTheme.colors.onPrimary.copy(
                    alpha = 0.4f
                ),
                strokeCap = StrokeCap.Round
            )
            Text(
                text = "1/5",
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onPrimary
            )
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "",
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }

}

@Preview
@Composable
fun PreviewScreenCreateTransaction() {
    BaseMainApp {
        ScreenCreateTransaction(it)
    }
}