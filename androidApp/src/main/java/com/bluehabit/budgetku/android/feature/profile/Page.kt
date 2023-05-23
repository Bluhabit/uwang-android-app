/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.profile

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.AnnotationTextItem
import com.bluehabit.budgetku.android.components.CardPointProfile
import com.bluehabit.budgetku.android.components.CardUpgradeToPremium
import com.bluehabit.budgetku.android.components.ChildMenu
import com.bluehabit.budgetku.android.components.HeaderSection
import com.bluehabit.budgetku.android.components.ItemAccount
import com.bluehabit.budgetku.android.components.ItemAccountPlaceholder
import com.bluehabit.budgetku.android.components.ItemMenuProfile
import com.bluehabit.budgetku.android.components.ParentMenu
import com.bluehabit.budgetku.android.feature.editProfile.EditProfile
import com.bluehabit.budgetku.android.ui.Error
import com.bluehabit.budgetku.android.ui.Grey500
import com.bluehabit.budgetku.android.ui.Grey900
import com.bluehabit.budgetku.android.ui.Yellow50
import com.bluehabit.budgetku.android.ui.Yellow800

object Profile {
    const val routeName = "Profile"
    val menus: List<ParentMenu> = listOf(
        ParentMenu(
            title = "Pengaturan Aplikasi",
            children = listOf(
                ChildMenu(
                    title = "Atur Pin",
                    icon = R.drawable.ic_menu_profile_atur_pin
                ),
                ChildMenu(
                    title = "Ubah Password",
                    icon = R.drawable.ic_menu_profile_ubah_password
                ),
                ChildMenu(
                    title = "Atur Pengingat",
                    icon = R.drawable.ic_menu_profile_atur_pengingat
                ),
                ChildMenu(
                    title = "Hapus Akun",
                    icon = R.drawable.ic_menu_profile_hapus_akun
                )
            )
        ),
        ParentMenu(
            title = "Kontak Kami",
            children = listOf(
                ChildMenu(
                    title = "Kirim Saran",
                    icon = R.drawable.ic_menu_profile_kirim_saran
                ),
                ChildMenu(
                    title = "Whatsapp",
                    icon = R.drawable.ic_menu_profile_ubah_password
                ),
                ChildMenu(
                    title = "Ikuti Sosial Media",
                    icon = R.drawable.ic_menu_profile_atur_pengingat
                ),
                ChildMenu(
                    title = "Berikan Rating Aplikasi",
                    icon = R.drawable.ic_menu_profile_rating
                )
            )
        ),
        ParentMenu(
            title = "Lainnya",
            children = listOf(
                ChildMenu(
                    title = "Tentang Aplikasi",
                    icon = R.drawable.ic_menu_profile_tentang_aplikasi
                ),
                ChildMenu(
                    title = "Syarat & Ketentuan",
                    icon = R.drawable.ic_menu_profile_syarat_ketentuan
                ),
                ChildMenu(
                    title = "Kebijakan Privasi",
                    icon = R.drawable.ic_menu_profile_atur_pengingat
                ),
                ChildMenu(
                    title = "FAQ",
                    icon = R.drawable.ic_menu_profile_faq
                )
            )
        )
    )
}

fun NavGraphBuilder.routeProfile(
    state: ApplicationState,
) {
    composable(Profile.routeName) {
        ScreenProfile(appState = state)
    }
}

@Composable
internal fun ScreenProfile(
    appState: ApplicationState,
) = UIWrapper<ProfileViewModel>(appState = appState) {
    val dataState by uiDataState.collectAsState()
    with(appState) {
        hideTopAppBar()

    }

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
                                        navigateUp()
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
                                        navigateSingleTop(EditProfile.routeName)
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
                                text = dataState.detailUser.fullName,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = dataState.detailUser.email,
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
                    items(dataState.accounts) {
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
        items(Profile.menus) {
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
        ScreenProfile(it)
    }
}