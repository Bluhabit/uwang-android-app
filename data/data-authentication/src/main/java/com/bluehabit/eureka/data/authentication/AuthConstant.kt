/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication

object AuthConstant {
    const val SESSION_OTP_ID = "sessionOtpId"
    const val SESSION_TOKEN_RESET_PASSWORD = "sessionToken"
    const val TOKEN_RESET_PASSWORD = "4adf-3ed"

    const val AUTH_SCREEN_SIGN_IN = 0
    const val AUTH_SCREEN_SIGN_UP = 1

    const val AUTH_SCREEN_OTP =1
    const val AUTH_SCREEN_COMPLETE_PROFILE =2

    const val AUTH_SCREEN_RESET_PASSWORD=1
    const val AUTH_SCREEN_INSTRUCTION_RESET_PASSWORD=2
    const val AUTH_SCREEN_LINK_CONFIRMATION=3
    const val AUTH_SCREEN_CREATE_PASSWORD=4
    const val AUTH_SCREEN_RESET_SUCCESS=5

    const val PROFILE_SCREEN_CHANGE_PASSWORD=1

    const val SEARCH_LIST_TASK=1
    const val EMPTY_LIST_TASK=0
    const val PROFILE_SCREEN_EDIT_PROFILE =1
}