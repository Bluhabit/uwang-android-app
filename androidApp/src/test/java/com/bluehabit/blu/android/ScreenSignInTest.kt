/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.blu.feature.authentication.auth.AuthScreen
import com.bluehabit.blu.feature.authentication.auth.AuthState
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
            var state by remember { mutableStateOf(AuthState()) }
            AuthScreen(uiContract = UIContract(
                rememberUIController(),
                state = state,
                mutation = {
                    state = it
                }
            ))
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