/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.bottomSheet

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R

data class BudgetCategory(
    val title: String = "",
    val categories: List<ItemBudgetCategory>,
)

data class ItemBudgetCategory(
    val title: String = "",
    val icon: Int = 0,
    val onClick: () -> Unit = {},
    val subCategories: List<ItemBudgetSubCategory> = listOf(),
)

data class ItemBudgetSubCategory(
    val title: String = "",
    val icon: Int = 0,
    val onClick: () -> Unit = {},
)

@Composable
fun BottomSheetAddBudgetCategory(
    onDismiss: () -> Unit = {},
    onSearch: (String) -> Unit,
    popularBudgetCategories: List<ItemBudgetCategory>,
    budgetCategories: List<BudgetCategory>,
    onCategorySelected: (String) -> Unit,
) {
    BaseBottomSheet(
        onDismiss = onDismiss,
        enableConfirmation = false,
        showButtonConfirmation = false,
        content = {
            Text(
                text = stringResource(R.string.title_budget_category_bottom_sheet),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(24.dp))
            BudgetCategoryContent(
                popularBudgetCategories = popularBudgetCategories,
                budgetCategories = budgetCategories,
                onClick = {
                    onCategorySelected(it)
                }
            )
        }
    )
}

@Composable
fun PopularBudgetCategory(
    itemBudgetCategories: List<ItemBudgetCategory>,
    onClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .background(Color(0xFFFAFAFA))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.text_popular_category),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(id = R.string.text_sub_popular_category),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Normal,
        )
        ItemBudgetCategoryContent(
            itemBudgetCategories = itemBudgetCategories,
            isShowIconBackground = false,
            onClickCategory = {
                onClick(it)
            }
        )
    }
}

@Composable
fun BudgetCategoryContent(
    popularBudgetCategories: List<ItemBudgetCategory>,
    budgetCategories: List<BudgetCategory>,
    onClick: (String) -> Unit = {},
) {
    LazyColumn {
        item {
            PopularBudgetCategory(
                itemBudgetCategories = popularBudgetCategories,
                onClick = {
                    onClick(it)
                },
            )
        }
        items(budgetCategories) {
            Column(
                modifier = Modifier.padding(top = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                )
                ItemBudgetCategoryContent(
                    itemBudgetCategories = it.categories,
                    onClickCategory = {
                        onClick(it)
                    }
                )
            }
        }
    }
}

@Composable
fun ItemBudgetCategoryContent(
    itemBudgetCategories: List<ItemBudgetCategory>,
    isShowIconBackground: Boolean = true,
    onClickCategory: (String) -> Unit,
) {
    val isExpanded = remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemBudgetCategories.forEach {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClickCategory(it.title)
                        },
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val iconModifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                    Box(
                        modifier = if (isShowIconBackground) {
                            iconModifier.background(Color(0xFFF5F5F5))
                        } else iconModifier
                    ) {
                        Image(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center),
                            imageVector = ImageVector.vectorResource(id = it.icon),
                            contentDescription = "",
                        )
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = it.title
                    )
                    if (it.subCategories.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    isExpanded.value = !isExpanded.value
                                },
                        ) {
                            Icon(
                                modifier = Modifier.align(Alignment.Center),
                                imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrow_down),
                                contentDescription = ""
                            )
                        }
                    }
                }
                if (it.subCategories.isNotEmpty()) {
                    AnimatedVisibility(visible = isExpanded.value) {
                        ItemBudgetSubCategoryContent(
                            itemBudgetSubCategories = it.subCategories,
                            onClickSubCategory = {
                                onClickCategory(it)
                            }
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun ItemBudgetSubCategoryContent(
    itemBudgetSubCategories: List<ItemBudgetSubCategory>,
    onClickSubCategory: (String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemBudgetSubCategories.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClickSubCategory(it.title) },
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF5F5F5))
                ) {
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                        imageVector = ImageVector.vectorResource(id = it.icon),
                        contentDescription = "",
                    )
                }
                Text(
                    modifier = Modifier.weight(1f),
                    text = it.title
                )
            }
        }
    }
}
