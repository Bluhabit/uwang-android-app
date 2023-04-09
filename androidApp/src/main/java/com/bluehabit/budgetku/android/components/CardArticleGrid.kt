/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.extensions.gridItems
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey700

@Composable
fun CardArticleGrid(
    title: String = "",
    message: String = "",
    image: Int = 0,
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2) - 32.dp

    Box(
        modifier = Modifier.padding(
            bottom = 16.dp,
        )
    ) {
        Column(
            modifier = Modifier
                .width(cardWidth)
                .height(IntrinsicSize.Max)
                .clip(MaterialTheme.shapes.medium)
                .border(
                    width = 1.dp,
                    color = Grey300,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal,
                    color = Grey700,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = true,
                        onClick = onClick
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Lihat Bantuan",
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewCardArticleGrid() {
    BaseMainApp {
        LazyColumn(content = {
            gridItems(
                3,
                columnCount = 2,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                paddingValues = PaddingValues(
                    horizontal = 20.dp
                )
            ) {

                CardArticleGrid(
                    title = "Cerdas Finansial",
                    message = "Yuk melek finansial bersama Budgetku.Tersedia course keuangan untuku",
                    image = R.drawable.ic_dummy_article,
                    onClick = {}
                )

            }
        })
    }
}