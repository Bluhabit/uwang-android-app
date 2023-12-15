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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import com.bluhabit.core.ui.theme.UwangTheme
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun TermAndConditionScreen(
    stateFlow: Flow<TermAndConditionState>,
    effectFlow: Flow<TermAndConditionEffect>,
    onAction: (TermAndConditionAction) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState()),
    ) {
        IconButton(
            onClick = {
                // TODO
            }) {
            Icon(
                painter = painterResource(id = com.bluehabit.core.ui.R.drawable.ic_arrow_back),
                contentDescription = "arrow back"
            )
        }
        val htmlText = "This is <b>HTML</b> text with <a href=\\\"https://www.example.com\\\">a link</a>."
        val spannedText = HtmlCompat.fromHtml(htmlText, FROM_HTML_MODE_LEGACY)
        // Display
        AndroidView(
            modifier = Modifier,
            factory = {
                MaterialTextView(it).apply {
                    autoLinkMask = Linkify.WEB_URLS
                    linksClickable = true
                    setLinkTextColor(Color.White.toArgb())
                }
            },
            update = {
                it.maxLines = Int.MAX_VALUE
                it.text = spannedText
            }
        )
    }
}

@Preview
@Composable
fun TermAndConditionScreenPreview() {
    UwangTheme {
        TermAndConditionScreen(
            stateFlow = flowOf(),
            effectFlow = flowOf(),
            onAction = {},
        )
    }
}
