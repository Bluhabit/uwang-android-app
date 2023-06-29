/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.routes

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.ChildMenu
import com.bluehabit.core.ui.components.ParentMenu

object Routes {
    object ChangePassword {
        const val routeName = "ChangePassword"
    }
    object CheckEmailResetPassword {
        const val routeName = "CheckEmailResetPassword"
    }

    object CompleteProfile {
        const val routeName = "CompleteProfile"
    }
    object CreateNewPassword {
        const val routeName = "CreateNewPassword"
    }
    object CreatePin {
        const val routeName = "CreatePin"
    }
    object EmailVerification {
        const val routeName = "EmailVerification"
    }
    object InputPin {
        const val routeName = "InputPin"
    }
    object Onboard {
        const val routeName = "Onboard"

        val images: List<Int> = listOf(
            R.drawable.onboard_1,
            R.drawable.onboard_2,
            R.drawable.onboard_3,
            R.drawable.onboard_4,
        )
        val title: List<Int> = listOf(
            R.string.onboard_title_1,
            R.string.onboard_title_2,
            R.string.onboard_title_3,
            R.string.onboard_title_4,
        )
        val subtitle: List<Int> = listOf(
            R.string.onboard_subtitle_1,
            R.string.onboard_subtitle_2,
            R.string.onboard_subtitle_3,
            R.string.onboard_subtitle_4,
        )
    }
    object ResetPassword {
        const val routeName = "ResetPassword"
    }
    object SignIn {
        const val routeName = "SignIn"
    }
    object SignUp {
        const val routeName = "SignUp"
    }

    object Splash {
        const val routeName = "Splash"
    }

    object CreateAccount {
        const val routeName = "CreateAccount"
    }
    object CreateAccountSaving {
        const val routeName = "CreateAccountSaving"
    }

    object CreateBudget {
        const val routeName = "CreateBudget"
    }
    object ListAccount {
        const val routeName = "ListAccount"

        val tabs = listOf(
            "Semua",
            "Tabungan",
            "Investasi"
        )
    }
    object ResultCreateBudget {
        const val routeName = "ResultCreateBudget"
    }
    object TutorialBudget {
        const val routeName = "TutorialBudget"
    }

    object CreatePost {
        const val routeName = "CreatePost"
    }
    object DetailPost {
        const val routeName = "DetailPost"
        const val argKey = "PostId"
        fun routeName() = "$routeName/{$argKey}"

        val navArg = listOf(
            navArgument(argKey) {
                type = NavType.StringType
            }
        )
    }
    object Budget {
        const val routeName = "Budget"
    }
    object Community {
        const val routeName = "Community"
    }

    object Home {
        const val routeName = "Home"
    }

    object Report {
        const val routeName = "Report"
    }
    object EditProfile {
        const val routeName = "EditProfile"
    }

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
    object CreateTransaction {
        const val routeName = "CreateTransaction"
    }

    object DetailTransaction {
        const val routeName = "DetailTransaction"

        const val argKey = "TransactionId"

        fun routeName() = "$routeName/{$argKey}"

        val navArgs = listOf<NamedNavArgument>(
            navArgument(argKey) {
                type = NavType.StringType
            }
        )
    }
    object EditTransaction {
        const val routeName = "EditTransaction"

        const val argKey = "TransactionId"

        fun routeName() = "$routeName/{$argKey}"

        val navArgs = listOf<NamedNavArgument>(
            navArgument(argKey) {
                type = NavType.StringType
            }
        )
    }
    object ListTransaction {
        const val routeName = "ListTransaction"
    }
}