/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components.alert

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey700
import com.bluehabit.budgetku.android.ui.LightBlue100

enum class AlertVariant {
    OUTLINED,
    FLAT
}

@Immutable
data class AlertVariantDefaults(
    val borderColor: Color,
    val borderWidth: Dp,
    val variant: AlertVariant,
    val backgroundColor: Color,
    val contentColor: Color,
    val modifier: Modifier = Modifier
)

@Immutable
object AlertDefaults {

    @Composable
    fun outlined(
        borderColor: Color = Grey300,
        borderWidth: Dp = 1.dp,
        contentColor: Color = MaterialTheme.colors.onSurface
    ): AlertVariantDefaults {
        return AlertVariantDefaults(
            borderColor = borderColor,
            variant = AlertVariant.OUTLINED,
            backgroundColor = Color.Transparent,
            borderWidth = borderWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .border(
                    width = borderWidth,
                    shape = MaterialTheme.shapes.medium,
                    color = borderColor
                )
                .padding(
                    all = 16.dp
                ),
            contentColor = contentColor
        )
    }

    @Composable
    fun flat(
        backgroundColor: Color = LightBlue100,
        contentColor: Color = Grey700
    ): AlertVariantDefaults {
        return AlertVariantDefaults(
            borderColor = Grey300,
            variant = AlertVariant.FLAT,
            backgroundColor = backgroundColor,
            borderWidth = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(backgroundColor)
                .padding(
                    all = 16.dp
                ),
            contentColor = contentColor
        )
    }
}

@Composable
fun Alert(
    icon: (@Composable () -> Unit)? = null,
    content: (@Composable RowScope.() -> Unit)? = null,
    variant: AlertVariantDefaults = AlertDefaults.outlined()
) {
    Row(
        modifier = variant.modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.invoke()
        Spacer(modifier = Modifier.width(8.dp))
        content?.invoke(this)
    }

}

@Composable
fun Alert(
    icon: Int = R.drawable.ic_like,
    text: AnnotatedString,
    tag: String = "",
    variant: AlertVariantDefaults = AlertDefaults.outlined(),
    onClick: () -> Unit = {}
) {
    Alert(
        icon = {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                )
            }
        },
        content = {
            ClickableText(
                text = text,
                style = MaterialTheme.typography.subtitle2,
                onClick = {
                    text.getStringAnnotations(
                        tag = "${tag}-$it",
                        start = it,
                        end = it
                    ).firstOrNull()?.let {
                        onClick()
                    }
                }
            )
        },
        variant = variant
    )
}

@Composable
fun Alert(
    icon: Int = R.drawable.ic_like,
    text: String = "",
    variant: AlertVariantDefaults = AlertDefaults.outlined()
) {
    Alert(
        icon = icon,
        text = buildAnnotatedString {
            append(text)
        },
        variant = variant
    )
}

@Composable
fun Alert(
    icon: ImageVector = Icons.Outlined.CheckCircle,
    text: AnnotatedString,
    tag: String = "",
    variant: AlertVariantDefaults = AlertDefaults.outlined(),
    onClick: () -> Unit = {}
) {
    Alert(
        icon = {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                )
            }
        },
        content = {
            ClickableText(
                text = text,
                style = MaterialTheme.typography.subtitle2,
                onClick = {
                    text.getStringAnnotations(
                        tag = "${tag}-$it",
                        start = it,
                        end = it
                    ).firstOrNull()?.let {
                        onClick()
                    }
                }
            )
        },
        variant = variant
    )
}

@Composable
fun Alert(
    icon: ImageVector = Icons.Outlined.CheckCircle,
    text: String = "",
    variant: AlertVariantDefaults = AlertDefaults.outlined()
) {
    Alert(
        icon = icon,
        text = buildAnnotatedString {
            append(text)
        },
        variant = variant
    )
}

@Preview
@Composable
fun PreviewAlert() {
    BaseMainApp {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                horizontal = 20.dp
            ),
            content = {
                item {
                    Alert(
                        icon = R.drawable.ic_add_account,
                        text = "Yuk, hubungkan semua akun finansialmu! Mau tau keuntungannya? cek di sini",
                        variant = AlertDefaults.outlined()
                    )
                }
                item {
                    Alert(
                        icon = R.drawable.ic_add_account,
                        text = "Yuk, hubungkan semua akun finansialmu! Mau tau keuntungannya? cek di sini",
                        variant = AlertDefaults.flat()
                    )
                }
                item {
                    Alert(
                        icon = Icons.Outlined.CheckCircle,
                        text = "Yuk, hubungkan semua akun finansialmu! Mau tau keuntungannya? cek di sini",
                        variant = AlertDefaults.flat()
                    )
                }

                //

                item {
                    Alert(
                        icon = R.drawable.ic_add_account,
                        text = buildAnnotatedString {
                            append("Yuk, hubungkan semua akun finansialmu! Mau tau keuntungannya? cek ")
                            append(" ")
                            pushStringAnnotation(tag = "tag", annotation = "tag")
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.primary,
                                    textDecoration = TextDecoration.None
                                )
                            ) {
                                append("di sini")
                            }
                            pop()
                        },
                        variant = AlertDefaults.outlined(),
                        onClick = {

                        },
                        tag = "tag"
                    )
                }
                item {
                    Alert(
                        icon = R.drawable.ic_add_account,
                        text = "Yuk, hubungkan semua akun finansialmu! Mau tau keuntungannya? cek di sini",
                        variant = AlertDefaults.flat()
                    )
                }
                item {
                    Alert(
                        icon = Icons.Outlined.CheckCircle,
                        text = "Yuk, hubungkan semua akun finansialmu! Mau tau keuntungannya? cek di sini",
                        variant = AlertDefaults.flat()
                    )
                }
            })
    }
}