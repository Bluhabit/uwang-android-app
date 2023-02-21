package com.bluehabit.budgetku.android.feature.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluehabit.budgetku.entity.UserModel
import com.bluehabit.budgetku.user.UserSDK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userSDK: UserSDK
) : ViewModel() {

    private val _userData = MutableStateFlow<String>("")
    val userData = _userData.asStateFlow()

    fun signInWithEmail(email: String, password: String) = with(viewModelScope) {
        launch {
            val data = userSDK.signInWithEmail(email, password)
            _userData.emit(
                if (data.first) {
                    data.third?.userId ?: "em"
                } else {
                    data.second
                }
            )
            Log.e("DATA", data.second)
        }
    }

    fun insertUser(
        data: UserModel
    ) = viewModelScope.launch {
        userSDK.insertUser(data)
    }

    fun getUserById(id: String) = viewModelScope.launch {

    }
}