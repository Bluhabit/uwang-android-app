/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.termAndCondition

import android.text.util.Linkify
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bluhabit.blu.android.common.loadHtmlFromAssets
import com.bluhabit.core.ui.theme.UwangTheme
import com.google.android.material.textview.MaterialTextView

@Composable
fun TermAndConditionScreen(
    onBackPressed: () -> Unit
) {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState()),
    ) {
        IconButton(
            onClick = {
                onBackPressed()
            }) {
            Icon(
                painter = painterResource(id = com.bluehabit.core.ui.R.drawable.ic_arrow_back),
                contentDescription = "arrow back"
            )
        }
        // Display
        AndroidView(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            factory = {
                MaterialTextView(it).apply {
                    autoLinkMask = Linkify.WEB_URLS
                    linksClickable = true
                    setLinkTextColor(Color.Blue.toArgb())
                    setTextColor(Color.Black.toArgb())
                }
            },
            update = {
                it.maxLines = Int.MAX_VALUE
                it.text = loadHtmlFromAssets(
                    context = context,
                    fileName = "termAndCondition.html"
                )
            }
        )
    }
}

@Preview
@Composable
fun TermAndConditionScreenPreview() {
    UwangTheme {
        TermAndConditionScreen(onBackPressed = {})
    }
}
