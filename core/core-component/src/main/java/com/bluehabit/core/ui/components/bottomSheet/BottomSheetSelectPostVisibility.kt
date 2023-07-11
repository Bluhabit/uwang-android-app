/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Blue800
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.Grey500

enum class PostVisibility {
    PUBLIC, ONLY_ME, ONLY_FOLLOWING, ONLY_MENTIONED
}
@Composable
fun BottomSheetSelectPostVisibility(
    onDismiss: () -> Unit = {},
    onSubmit: (PostVisibility) -> Unit = {},
    onVisibilitySelected: (PostVisibility) -> Unit = {},
    postVisibility: PostVisibility,
) {
    BaseBottomSheet(
        textConfirmation = stringResource(id = R.string.text_button_select_audience_create_post),
        showLineHeader = false,
        onDismiss = onDismiss,
        onConfirm = { onSubmit(postVisibility) },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.text_bottom_sheet_title_create_post),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier,
                text = stringResource(R.string.text_bottom_sheet_reset_create_post),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Blue800
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BottomSheetSelectPostVisibilityItem(
                postVisibility = PostVisibility.PUBLIC,
                isChecked = postVisibility,
                onSelected = { onVisibilitySelected(it) }
            )
            Divider(thickness = 1.dp, color = Grey300)
            BottomSheetSelectPostVisibilityItem(
                postVisibility = PostVisibility.ONLY_FOLLOWING,
                isChecked = postVisibility,
                onSelected = { onVisibilitySelected(it) }
            )
            Divider(thickness = 1.dp, color = Grey300)
            BottomSheetSelectPostVisibilityItem(
                postVisibility = PostVisibility.ONLY_MENTIONED,
                isChecked = postVisibility,
                onSelected = { onVisibilitySelected(it) }
            )
            Divider(thickness = 1.dp, color = Grey300)
            BottomSheetSelectPostVisibilityItem(
                postVisibility = PostVisibility.ONLY_ME,
                isChecked = postVisibility,
                onSelected = { onVisibilitySelected(it) }
            )
            Divider(thickness = 1.dp, color = Grey300)
        }
    }
}

@Composable
fun BottomSheetSelectPostVisibilityItem(
    postVisibility: PostVisibility,
    isChecked: PostVisibility,
    onSelected: (PostVisibility) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelected(postVisibility) }
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = when (postVisibility) {
                PostVisibility.PUBLIC -> stringResource(id = R.string.text_bottom_sheet_public_create_post)
                PostVisibility.ONLY_ME -> stringResource(id = R.string.text_bottom_sheet_only_me_create_post)
                PostVisibility.ONLY_FOLLOWING -> stringResource(id = R.string.text_bottom_sheet_only_follow_create_post)
                PostVisibility.ONLY_MENTIONED -> stringResource(id = R.string.text_bottom_sheet_only_mention_create_post)
            },
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Normal,
            color = Grey500,
        )
        if (isChecked == postVisibility) {
            Image(painter = painterResource(id = R.drawable.ic_check), contentDescription = "")
        }
    }
}