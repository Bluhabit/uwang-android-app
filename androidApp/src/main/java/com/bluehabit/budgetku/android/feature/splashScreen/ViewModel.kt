package com.bluehabit.budgetku.android.feature.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluehabit.budgetku.sdk.auth.AuthSDK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authSDK: AuthSDK
) : ViewModel() {

    fun checkIfUserLoggedIn(
        cb:(isLoggedIn:Boolean)->Unit
    )= with(viewModelScope){
        launch {
            cb(authSDK.isLoggedIn())
        }
    }
}