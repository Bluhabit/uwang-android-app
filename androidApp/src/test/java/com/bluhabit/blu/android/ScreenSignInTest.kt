/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.bluhabit.blu.android.presentation.authentication.onboard.OnboardScreen
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
class ScreenSignInTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        ShadowLog.stream = System.out //logcat to console
        composeRule.setContent {

            OnboardScreen(
                navHostController = rememberNavController(),
                stateFlow = flowOf(),
                effectFlow = flowOf(),
                onAction = {
                }
            )
        }
    }

    @Test
    fun `should show content at first mount`() {
        //should display sign in at first
        composeRule.onNodeWithText("Selamat datang di Gawean").assertIsDisplayed()
        composeRule.onNodeWithText("Daftar atau masuk agar bisa atur tugas lebih mudah dan selalu produktif.").assertIsDisplayed()
    }

    @Test
    fun `when tab "Daftar" clicked show sign up screen`() {
        //when tab "Daftar" clicked
        composeRule.onNodeWithTag("tab_auth_1").assertIsDisplayed().performClick()
        composeRule.onNodeWithTag("container_sign_up").assertIsDisplayed()
        composeRule.onNodeWithTag("tab_auth_0").assertIsDisplayed().performClick()
        composeRule.onNodeWithTag("container_sign_up").assertDoesNotExist()
    }

    @Test
    fun `when tab "Masuk" clicked show sign in screen`() {
        //when tab "Daftar" clicked
        composeRule.onNodeWithTag("tab_auth_0").assertIsDisplayed().performClick()
        composeRule.onNodeWithTag("container_sign_in").assertIsDisplayed()
        composeRule.onNodeWithTag("tab_auth_1").assertIsDisplayed().performClick()
        composeRule.onNodeWithTag("container_sign_in").assertDoesNotExist()
    }
}