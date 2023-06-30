/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.profile.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.listener.BaseEventListener
import app.trian.mvi.ui.internal.listener.EventListener
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.AnnotationTextItem
import com.bluehabit.core.ui.components.CardPointProfile
import com.bluehabit.core.ui.components.CardUpgradeToPremium
import com.bluehabit.core.ui.components.HeaderSection
import com.bluehabit.core.ui.components.ItemAccount
import com.bluehabit.core.ui.components.ItemAccountPlaceholder
import com.bluehabit.core.ui.components.ItemMenuProfile
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.Error
import com.bluehabit.core.ui.theme.Grey500
import com.bluehabit.core.ui.theme.Grey900
import com.bluehabit.core.ui.theme.Yellow50
import com.bluehabit.core.ui.theme.Yellow800

@Navigation(
    route = Routes.Profile.routeName,
    viewModel = ProfileViewModel::class
)
@Composable
fun ProfileScreen(
    uiContract: UIContract<ProfileState, ProfileIntent, ProfileAction>,
    event: BaseEventListener = EventListener(),
) = UIWrapper(uiContract) {

    LazyColumn(content = {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        280.dp
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_profile),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.FillWidth
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 20.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_left_profile),
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .clickable(
                                    enabled = true,
                                    onClick = {
                                        navigator.navigateUp()
                                    }
                                )
                        )
                        Text(
                            text = "Profile",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            color = Grey900
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_edit_profile),
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .clickable(
                                    enabled = true,
                                    onClick = {
                                        navigator.navigateSingleTop(Routes.EditProfile.routeName)
                                    }
                                )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                            .shadow(
                                elevation = 2.dp,
                                shape = MaterialTheme.shapes.medium
                            )
                            .background(MaterialTheme.colors.surface)
                            .padding(
                                horizontal = 16.dp,
                                vertical = 16.dp
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .width(64.dp)
                                .height(70.dp)
                        ) {
                            Image(
                                painter = painterResource(id = com.bluehabit.budgetku.data.R.drawable.dummy_avatar_4),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(64.dp)
                                    .align(Alignment.TopCenter)
                                    .clip(CircleShape)
                            )
                            Text(
                                text = "Gratis",
                                color = Yellow800,
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .clip(MaterialTheme.shapes.small)
                                    .background(Yellow50)
                                    .padding(
                                        horizontal = 8.dp,
                                        vertical = 4.dp
                                    )
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column() {
                            Text(
                                text = state.detailUser.fullName,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.detailUser.email,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_coins_profile),
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "2120 points",
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }

                    }
                }


            }
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),
            ) {
                CardUpgradeToPremium(
                    onClick = {

                    }
                )
            }
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardPointProfile(
                    text = listOf(
                        AnnotationTextItem.Text(
                            "Masuk dan ambil",
                            style = MaterialTheme.typography.body2.copy(
                                color = Grey500,
                                fontWeight = FontWeight.Normal
                            )
                        ),
                        AnnotationTextItem.Text(
                            "40",
                            style = MaterialTheme.typography.body2.copy(
                                color = Grey900,
                                fontWeight = FontWeight.Bold
                            )
                        ),
                        AnnotationTextItem.Text(
                            "koin",
                            style = MaterialTheme.typography.body2.copy(
                                color = Grey500,
                                fontWeight = FontWeight.Normal
                            )
                        ),
                    ),
                    textButton = "Collect"
                )
                Spacer(modifier = Modifier.width(10.dp))
                CardPointProfile(
                    text = listOf(
                        AnnotationTextItem.Text(
                            "Cari tau rank keuanganmu",
                            style = MaterialTheme.typography.body2.copy(
                                color = Grey500,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    ),
                    textButton = "Collect"
                )
            }
        }
        item {
            HeaderSection(
                title = "Akun Finansialmu",
                onClick = {

                }
            )
        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    item {
                        Spacer(modifier = Modifier.width(20.dp))
                        ItemAccountPlaceholder(
                            onClick = {

                            }
                        )
                    }
                    items(state.accounts) {
                        ItemAccount(
                            icon = it.icon,
                            accountBalance = it.accountBalance,
                            accountBankName = it.accountName,
                            onClick = {

                            }
                        )
                    }
                })
        }
        items(Routes.Profile.menus) {
            ItemMenuProfile(
                name = it.title,
                children = it.children
            )
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Hapus Akun",
                        style = MaterialTheme.typography.button,
                        color = Error,
                    )
                }
            }
        }
    })

}

@Preview
@Composable
fun PreviewScreenProfile() {
    BaseMainApp {
       ProfileScreen(
            uiContract = UIContract(
                controller = it,
                state= ProfileState()
            )
        )
    }
}