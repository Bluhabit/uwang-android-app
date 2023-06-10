/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.bluehabit.budgetku.feature.authentication.changePassword.ChangePasswordViewModel
import com.bluehabit.budgetku.feature.authentication.changePassword.ScreenChangePassword
import com.bluehabit.budgetku.feature.authentication.checkEmailResetPassword.CheckEmailResetPasswordViewModel
import com.bluehabit.budgetku.feature.authentication.checkEmailResetPassword.ScreenCheckEmailResetPassword
import com.bluehabit.budgetku.feature.authentication.completeProfile.CompleteProfileViewModel
import com.bluehabit.budgetku.feature.authentication.completeProfile.ScreenCompleteProfile
import com.bluehabit.budgetku.feature.authentication.createNewPassword.CreateNewPasswordViewModel
import com.bluehabit.budgetku.feature.authentication.createNewPassword.ScreenCreateNewPassword
import com.bluehabit.budgetku.feature.authentication.emailVerification.EmailVerificationViewModel
import com.bluehabit.budgetku.feature.authentication.emailVerification.ScreenEmailVerification
import com.bluehabit.budgetku.feature.authentication.inputPin.InputPinViewModel
import com.bluehabit.budgetku.feature.authentication.inputPin.ScreenInputPin
import com.bluehabit.budgetku.feature.authentication.onboarding.OnboardState
import com.bluehabit.budgetku.feature.authentication.onboarding.OnboardViewModel
import com.bluehabit.budgetku.feature.authentication.onboarding.ScreenOnboard
import com.bluehabit.budgetku.feature.authentication.resetPassword.ResetPasswordViewModel
import com.bluehabit.budgetku.feature.authentication.resetPassword.ScreenResetPassword
import com.bluehabit.budgetku.feature.authentication.signIn.ScreenSignIn
import com.bluehabit.budgetku.feature.authentication.signIn.SignInViewModel
import com.bluehabit.budgetku.feature.authentication.signUp.ScreenSignUp
import com.bluehabit.budgetku.feature.authentication.signUp.SignUpViewModel
import com.bluehabit.budgetku.feature.authentication.splashScreen.ScreenSplash
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashState
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashViewModel
import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.UIListener
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.pageWrapper
import com.bluehabit.core.ui.routes.AuthenticationConstants
import com.bluehabit.core.ui.routes.Routes.ChangePassword
import com.bluehabit.core.ui.routes.Routes.CheckEmailResetPassword
import com.bluehabit.core.ui.routes.Routes.CompleteProfile
import com.bluehabit.core.ui.routes.Routes.CreateNewPassword
import com.bluehabit.core.ui.routes.Routes.EmailVerification
import com.bluehabit.core.ui.routes.Routes.InputPin
import com.bluehabit.core.ui.routes.Routes.Onboard
import com.bluehabit.core.ui.routes.Routes.ResetPassword
import com.bluehabit.core.ui.routes.Routes.SignIn
import com.bluehabit.core.ui.routes.Routes.SignUp
import com.bluehabit.core.ui.routes.Routes.Splash

fun NavGraphBuilder.authenticationRoute(
    uiController: UIController
) {
    navigation(
        route = AuthenticationConstants.parentRoute,
        startDestination = Splash.routeName
    ) {
        pageWrapper<SplashViewModel>(
            route = Splash.routeName,
            controller = uiController
        ) {
            ScreenSplash(
                invoker = UIListener(
                    controller = uiController,
                    state = SplashState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<OnboardViewModel>(
            route = Onboard.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenOnboard(
                state=state,
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<SignInViewModel>(
            route = SignIn.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenSignIn(
                state=state,
                invoker = UIListener(
                    controller = uiController,
                    state =state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<SignUpViewModel>(
            route = SignUp.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenSignUp(
                state=state,
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<EmailVerificationViewModel>(
            route = EmailVerification.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            val data by uiDataState.collectAsState()
            ScreenEmailVerification(
                state=state,
                data=data,
                invoker = UIListenerData(
                    controller = uiController,
                    state = state,
                    data = data,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<CompleteProfileViewModel>(
            route = CompleteProfile.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenCompleteProfile(
                state=state,
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<InputPinViewModel>(
            route = InputPin.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenInputPin(
                state=state,
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<ChangePasswordViewModel>(
            route = ChangePassword.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenChangePassword(
                state=state,
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<ResetPasswordViewModel>(
            route = ResetPassword.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenResetPassword(
                state=state,
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<CreateNewPasswordViewModel>(
            route = CreateNewPassword.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenCreateNewPassword(
                state=state,
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<CheckEmailResetPasswordViewModel>(
            route = CheckEmailResetPassword.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            ScreenCheckEmailResetPassword(
                state=state,
                invoker = UIListener(
                    controller = uiController,
                    state = state,
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
    }
}