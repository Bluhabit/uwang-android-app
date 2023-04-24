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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey100

@Composable
fun ItemTutorial(
    title:String="",
    image:Int=0,
    onClick:()->Unit={}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2) - 40.dp

    Column(
        modifier = Modifier
            .width(cardWidth)
            .height(cardWidth + 40.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClick()
            }
            .border(
                width = 1.dp,
                color = Grey100,
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f)
                .padding(
                    horizontal = 16.dp
                ),
            contentScale = ContentScale.FillBounds
        )

    }

}

@Preview
@Composable
fun PreviewItemTutorial() {
    BaseMainApp {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(3) {
                    ItemTutorial(
                        title="Cara Transaksi di Budgetku ",
                        image = R.drawable.ic_dummy_tutorial
                    )
                }
            }
        )
    }
}