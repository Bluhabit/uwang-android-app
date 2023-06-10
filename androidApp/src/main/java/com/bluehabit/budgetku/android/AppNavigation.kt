/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.bluehabit.budgetku.android.feature.createAccount.routeCreateAccount
import com.bluehabit.budgetku.android.feature.createAccountSaving.routeCreateAccountSaving
import com.bluehabit.budgetku.android.feature.createBudget.routeCreateBudget
import com.bluehabit.budgetku.android.feature.createPost.routeCreatePost
import com.bluehabit.budgetku.android.feature.createTransaction.routeCreateTransaction
import com.bluehabit.budgetku.android.feature.dashboard.budget.routeBudget
import com.bluehabit.budgetku.android.feature.dashboard.community.routeCommunity
import com.bluehabit.budgetku.android.feature.dashboard.home.routeHome
import com.bluehabit.budgetku.android.feature.dashboard.report.routeReport
import com.bluehabit.budgetku.android.feature.detailPost.routeDetailPost
import com.bluehabit.budgetku.android.feature.detailTransaction.routeDetailTransaction
import com.bluehabit.budgetku.android.feature.editProfile.routeEditProfile
import com.bluehabit.budgetku.android.feature.editTransaction.routeEditTransaction
import com.bluehabit.budgetku.android.feature.listAccount.routeListAccount
import com.bluehabit.budgetku.android.feature.listTransaction.routeListTransaction
import com.bluehabit.budgetku.feature.authentication.onboarding.Onboard
import com.bluehabit.budgetku.feature.authentication.onboarding.OnboardState
import com.bluehabit.budgetku.feature.authentication.onboarding.OnboardViewModel
import com.bluehabit.budgetku.android.feature.profile.routeProfile
import com.bluehabit.budgetku.android.feature.resultCreateBudget.routeResultCreateBudget
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashState
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashViewModel
import com.bluehabit.budgetku.android.feature.tutorialBudget.routeTutorialBudget
import com.bluehabit.budgetku.feature.authentication.AuthenticationConstants
import com.bluehabit.budgetku.feature.authentication.authenticationRoute
import com.bluehabit.budgetku.feature.authentication.changePassword.ChangePassword
import com.bluehabit.budgetku.feature.authentication.changePassword.ChangePasswordState
import com.bluehabit.budgetku.feature.authentication.changePassword.ChangePasswordViewModel
import com.bluehabit.budgetku.feature.authentication.changePassword.ScreenChangePassword
import com.bluehabit.budgetku.feature.authentication.checkEmailResetPassword.CheckEmailResetPassword
import com.bluehabit.budgetku.feature.authentication.checkEmailResetPassword.CheckEmailResetPasswordState
import com.bluehabit.budgetku.feature.authentication.checkEmailResetPassword.CheckEmailResetPasswordViewModel
import com.bluehabit.budgetku.feature.authentication.checkEmailResetPassword.ScreenCheckEmailResetPassword
import com.bluehabit.budgetku.feature.authentication.completeProfile.CompleteProfile
import com.bluehabit.budgetku.feature.authentication.completeProfile.CompleteProfileState
import com.bluehabit.budgetku.feature.authentication.completeProfile.CompleteProfileViewModel
import com.bluehabit.budgetku.feature.authentication.completeProfile.ScreenCompleteProfile
import com.bluehabit.budgetku.feature.authentication.createNewPassword.CreateNewPassword
import com.bluehabit.budgetku.feature.authentication.createNewPassword.CreateNewPasswordState
import com.bluehabit.budgetku.feature.authentication.createNewPassword.CreateNewPasswordViewModel
import com.bluehabit.budgetku.feature.authentication.createNewPassword.ScreenCreateNewPassword
import com.bluehabit.budgetku.feature.authentication.emailVerification.EmailVerification
import com.bluehabit.budgetku.feature.authentication.emailVerification.EmailVerificationDataState
import com.bluehabit.budgetku.feature.authentication.emailVerification.EmailVerificationState
import com.bluehabit.budgetku.feature.authentication.emailVerification.EmailVerificationViewModel
import com.bluehabit.budgetku.feature.authentication.emailVerification.ScreenEmailVerification
import com.bluehabit.budgetku.feature.authentication.inputPin.InputPin
import com.bluehabit.budgetku.feature.authentication.inputPin.InputPinState
import com.bluehabit.budgetku.feature.authentication.inputPin.InputPinViewModel
import com.bluehabit.budgetku.feature.authentication.inputPin.ScreenInputPin
import com.bluehabit.budgetku.feature.authentication.onboarding.ScreenOnboard
import com.bluehabit.budgetku.feature.authentication.resetPassword.ResetPassword
import com.bluehabit.budgetku.feature.authentication.resetPassword.ResetPasswordState
import com.bluehabit.budgetku.feature.authentication.resetPassword.ResetPasswordViewModel
import com.bluehabit.budgetku.feature.authentication.resetPassword.ScreenResetPassword
import com.bluehabit.budgetku.feature.authentication.signIn.ScreenSignIn
import com.bluehabit.budgetku.feature.authentication.signIn.SignIn
import com.bluehabit.budgetku.feature.authentication.signIn.SignInState
import com.bluehabit.budgetku.feature.authentication.signIn.SignInViewModel
import com.bluehabit.budgetku.feature.authentication.signUp.ScreenSignUp
import com.bluehabit.budgetku.feature.authentication.signUp.SignUp
import com.bluehabit.budgetku.feature.authentication.signUp.SignUpState
import com.bluehabit.budgetku.feature.authentication.signUp.SignUpViewModel
import com.bluehabit.budgetku.feature.authentication.splashScreen.ScreenSplash
import com.bluehabit.budgetku.feature.authentication.splashScreen.Splash
import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.UIWrapperListener
import com.bluehabit.core.ui.UIWrapperListenerData
import com.bluehabit.core.ui.pageWrapper

@Composable
fun AppNavigation(
    uiController: UIController
) {
    NavHost(
        navController = uiController.router,
        startDestination = AuthenticationConstants.parentRoute
    ) {
        authenticationRoute(uiController = uiController)

        routeHome(
            state = uiController
        )
        routeCommunity(
            state = uiController
        )
        routeBudget(
            state = uiController
        )
        routeReport(
            state = uiController
        )
        routeCreateBudget(
            state = uiController
        )

        routeResultCreateBudget(
            state = uiController
        )

        routeCreateTransaction(
            state = uiController
        )
        routeTutorialBudget(
            state = uiController
        )
        routeCreateAccount(
            state = uiController
        )
        routeListAccount(
            state = uiController
        )
        routeEditProfile(
            state = uiController
        )
        routeDetailTransaction(
            state = uiController
        )
        routeEditTransaction(
            state = uiController
        )
        routeCreateAccountSaving(
            state = uiController
        )
        routeEditTransaction(
            state = uiController
        )
        routeCreatePost(
            state = uiController
        )

        routeListTransaction(
            state = uiController
        )
        routeDetailPost(
            state = uiController
        )
        routeProfile(
            state = uiController
        )

    }
}