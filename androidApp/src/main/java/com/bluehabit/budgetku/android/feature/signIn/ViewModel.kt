package com.bluehabit.budgetku.android.feature.signIn

import android.util.Log
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

@HiltViewModel
class UserViewModel @Inject constructor(
    private val authSDK: AuthSDK,
    private val userSDK: UserSDK
) : ViewModel() {

    private val _userData = MutableStateFlow<String>("")
    val userData = _userData.asStateFlow()

    fun signInWithEmail(email: String, password: String, cb: suspend (Boolean, String) -> Unit) = with(viewModelScope) {
        launch {
            authSDK.signInWithEmail("", "")
                .collect {
                    when (it) {
                        is Response.Error -> cb(false, it.message)
                        Response.Loading -> Unit
                        is Response.Result -> cb(true, "")
                    }
                }
        }
    }

    fun signInGoogle(result: Task<GoogleSignInAccount>?, cb: suspend (Boolean, String) -> Unit) = with(viewModelScope) {
        launch {
            val token = result!!.await()
            authSDK.signInGoogle(token.idToken.orEmpty())
                .collect {
                    when (it) {
                        is Response.Error -> cb(false, it.message)
                        Response.Loading -> Unit
                        is Response.Result -> cb(true, "")
                    }
                }

        }
    }


}