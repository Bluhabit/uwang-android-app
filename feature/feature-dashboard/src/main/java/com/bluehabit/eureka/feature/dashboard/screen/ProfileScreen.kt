/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.getScreenWidth
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonLogout
import com.bluehabit.core.ui.components.chart.BarChartView
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray100
import com.bluehabit.core.ui.theme.Gray600
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.eureka.feature.dashboard.DashboardState

@Composable
fun ProfileScreen(
    state: DashboardState,
    onClickNotification: () -> Unit = {},
) {
    val context = LocalContext.current
    val screenWidth = context.getScreenWidth() - 40.dp
    val itemWidth = (screenWidth / 3)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Profil",
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    fontWeight = FontWeight.W500,
                    color = Gray600
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onClickNotification) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bell),
                        contentDescription = stringResource(id = R.string.content_description_logo)
                    )
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(
                        horizontal = 18.dp,
                        vertical = 10.dp
                    )
            ) {
                Row {
                    Image(
                        painter = painterResource(id = com.bluehabit.eureka.data.R.drawable.dummy_avatar),
                        contentDescription = "",
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row {
                            Text(
                                text = "Olivia",
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Text(text = "olivia@example.com")
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Divider()
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.width(itemWidth),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Total Tugas")
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(itemWidth - 10.dp)
                                .height(itemWidth / 2)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Gray100)
                        ) {
                            Text(
                                text = "11",
                                modifier = Modifier.align(Alignment.Center),
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.W700
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.width(itemWidth),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Berjalan")
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(itemWidth - 10.dp)
                                .height(itemWidth / 2)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Gray100)
                        ) {
                            Text(
                                text = "11",
                                modifier = Modifier.align(Alignment.Center),
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.W700
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.width(itemWidth),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Selesai")
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(itemWidth - 10.dp)
                                .height(itemWidth / 2)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Gray100)
                        ) {
                            Text(
                                text = "11",
                                modifier = Modifier.align(Alignment.Center),
                                style = MaterialTheme.typography.body1,
                                fontWeight = FontWeight.W700
                            )
                        }
                    }
                }
            }
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
        }
        item {
            BarChartView(
                title = "Aktifitas",
                maxAxis = 8f,
                labels = listOf(),
                items = listOf()
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
        }
        item {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .clickable { }
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 4.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = "Notifikasi",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF101828),
                    )
                )
                Switch(
                    checked = true,
                    onCheckedChange = {

                    }
                )
            }
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .clickable { }
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = "Ubah Password",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF101828),
                    )
                )
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    tint = Primary600,
                    contentDescription = "",
                )
            }
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .clickable { }
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lapor Bug",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF101828),
                    )
                )
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    tint = Primary600,
                    contentDescription = "",
                )
            }
        }
        item {
            ButtonLogout()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ProfileScreenPreview() {
    GaweanTheme {
        ProfileScreen(DashboardState(fullName = "Olivia Rhye"))
    }
}