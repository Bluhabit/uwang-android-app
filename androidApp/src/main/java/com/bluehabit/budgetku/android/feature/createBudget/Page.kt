/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createBudget

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
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.AnnotationTextItem
import com.bluehabit.budgetku.android.components.ButtonPrimary
import com.bluehabit.budgetku.android.components.FormInput
import com.bluehabit.budgetku.android.components.TextWithAction
import com.bluehabit.budgetku.android.ui.Yellow600

object CreateBudget {
    const val routeName = "CreateBudget"
}

fun NavGraphBuilder.routeCreateBudget(
    state: ApplicationState,
) {
    composable(CreateBudget.routeName) {
        ScreenCreateBudget(appState = state)
    }
}

@Composable
internal fun ScreenCreateBudget(
    appState: ApplicationState,
) = UIWrapper<CreateBudgetViewModel>(appState = appState) {
    with(appState) {
        setupTopAppBar {
            TopAppBar(
                elevation = 0.dp,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navigateUp()
                        }
                    )
                },
                title = {
                    Text(
                        text = "Atur Total Budget",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_hero_create_budget),
                    contentDescription = "",
                    modifier = Modifier.size(240.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.surface)
                    .padding(
                        all = 16.dp
                    )
            ) {
                Text(
                    text = "Budget",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                FormInput(
                    value = "",
                    label = "Budget per Bulan",
                    placeholder = "0",
                    leadingIcon = {
                        Text(
                            text = "Rp",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Medium
                        )
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_idea_star),
                        contentDescription = "",
                        tint = Yellow600
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Pakai rule 50% kebutuhan, 20% keinginan,20% ditabung",
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                )
                .background(MaterialTheme.colors.surface)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
            ) {
                Image(
                    painter=painterResource(id = R.drawable.ic_idea_bulb),
                    contentDescription = ""
                )
                TextWithAction(
                    labels = listOf(
                        AnnotationTextItem.Text("Cari tau keuntungan bikin budget "),
                        AnnotationTextItem.Button("di sini", true)
                    ),
                    onTextClick = {}
                )
            }
            Divider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
            ) {
                ButtonPrimary(text = "Simpan") {

                }
            }
        }
    }


}


@Preview
@Composable
fun PreviewScreenCreateBudget() {
    BaseMainApp(
        topAppBar = {
            TopAppBar(
                elevation = 0.dp,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = ""
                    )
                },
                title = {
                    Text(
                        text = "Atur Total Budget",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) {
        ScreenCreateBudget(it)
    }
}