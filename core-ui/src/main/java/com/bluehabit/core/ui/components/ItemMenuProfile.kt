/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.ui.BudgetKuTheme
import com.bluehabit.budgetku.android.ui.Grey100
import com.bluehabit.budgetku.android.ui.Grey500

data class ChildMenu(
    val icon: Int,
    val title: String
)

data class ParentMenu(
    val title: String = "",
    val children: List<ChildMenu> = listOf()
)

@Composable
fun ItemMenuProfile(
    name: String = "",
    children: List<ChildMenu> = listOf(),
    onItemClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        HeaderSection(
            title = name,
            showArrow = false
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            children.forEach {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            enabled = true,
                            onClick = onItemClicked
                        )
                        .padding(
                            vertical = 4.dp,
                            horizontal = 20.dp
                        ),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Grey100)
                                    .padding(
                                        all = 8.dp
                                    )
                            ) {
                                Image(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = "",
                                    modifier = Modifier.align(Alignment.Center),
                                    contentScale = ContentScale.Fit
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = it.title,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colors.onSurface
                            )
                        }


                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Grey500,
                            modifier = Modifier.clickable(
                                enabled = true,
                                onClick = {}
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewItemMenuProfile() {
    BudgetKuTheme {
        LazyColumn(
            modifier = Modifier.background(
                MaterialTheme.colors.surface
            ),
            content = {
                item {
                    ItemMenuProfile(
                        name = "Pengaturan Aplikasi",
                        children = listOf(
                            ChildMenu(
                                icon = com.bluehabit.budgetku.data.R.drawable.dummy_category_tagihan,
                                title = "Kritik & Saran"
                            )
                        )
                    )
                }
            })
    }
}