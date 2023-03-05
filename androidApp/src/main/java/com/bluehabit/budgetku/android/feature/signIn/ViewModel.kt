/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluehabit.budgetku.sdk.auth.AuthSDK
import com.bluehabit.budgetku.sdk.user.UserSDK
import com.bluehabit.budgetku.utils.Response
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

