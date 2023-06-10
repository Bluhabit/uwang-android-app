/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
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
import com.bluehabit.budgetku.feature.authentication.onboarding.Onboard
import com.bluehabit.budgetku.feature.authentication.onboarding.OnboardState
import com.bluehabit.budgetku.feature.authentication.onboarding.OnboardViewModel
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
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashState
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashViewModel
import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.UIWrapperListener
import com.bluehabit.core.ui.UIWrapperListenerData
import com.bluehabit.core.ui.pageWrapper

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
                invoker = UIWrapperListener(
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
            ScreenOnboard(
                invoker = UIWrapperListener(
                    controller = uiController,
                    state = OnboardState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<SignInViewModel>(
            route = SignIn.routeName,
            controller = uiController
        ) {
            ScreenSignIn(
                invoker = UIWrapperListener(
                    controller = uiController,
                    state = SignInState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<SignUpViewModel>(
            route = SignUp.routeName,
            controller = uiController
        ) {
            ScreenSignUp(
                invoker = UIWrapperListener(
                    controller = uiController,
                    state = SignUpState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<EmailVerificationViewModel>(
            route = EmailVerification.routeName,
            controller = uiController
        ) {
            ScreenEmailVerification(
                invoker = UIWrapperListenerData(
                    controller = uiController,
                    state = EmailVerificationState(),
                    data = EmailVerificationDataState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<CompleteProfileViewModel>(
            route = CompleteProfile.routeName,
            controller = uiController
        ) {
            ScreenCompleteProfile(
                invoker = UIWrapperListener(
                    controller = uiController,
                    state = CompleteProfileState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<InputPinViewModel>(
            route = InputPin.routeName,
            controller = uiController
        ) {
            ScreenInputPin(
                invoker = UIWrapperListener(
                    controller = uiController,
                    state = InputPinState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<ChangePasswordViewModel>(
            route = ChangePassword.routeName,
            controller = uiController
        ) {
            ScreenChangePassword(
                invoker = UIWrapperListener(
                    controller = uiController,
                    state = ChangePasswordState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<ResetPasswordViewModel>(
            route = ResetPassword.routeName,
            controller = uiController
        ) {
            ScreenResetPassword(
                invoker = UIWrapperListener(
                    controller = uiController,
                    state = ResetPasswordState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<CreateNewPasswordViewModel>(
            route = CreateNewPassword.routeName,
            controller = uiController
        ) {
            ScreenCreateNewPassword(
                invoker = UIWrapperListener(
                    controller = uiController,
                    state = CreateNewPasswordState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }

        pageWrapper<CheckEmailResetPasswordViewModel>(
            route = CheckEmailResetPassword.routeName,
            controller = uiController
        ) {
            ScreenCheckEmailResetPassword(
                invoker = UIWrapperListener(
                    controller = uiController,
                    state = CheckEmailResetPasswordState(),
                    commit = ::commit,
                    dispatcher = ::dispatch
                )
            )
        }
    }
}