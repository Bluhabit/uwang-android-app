/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.data.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.bluhabit.blu.data.common.Response
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

class GoogleAuthContract : ActivityResultContract<Int, Response<Task<GoogleSignInAccount>>>() {
    override fun createIntent(context: Context, input: Int): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("616208190167-aget8lort8gj59osgs4doe9g9i5bnhfj.apps.googleusercontent.com")
            .requestId()
            .requestProfile()
            .build()

        val gsi = GoogleSignIn.getClient(
            context,
            gso
        )
        gsi.signOut()
        return gsi.signInIntent
    }


    override fun parseResult(resultCode: Int, intent: Intent?): Response<Task<GoogleSignInAccount>> {
        return when (resultCode) {
            Activity.RESULT_OK -> {
                val data = GoogleSignIn.getSignedInAccountFromIntent(intent)
                return Response.Result(data)
            }

            else -> {
                Response.Error(
                    intent?.extras?.keySet()?.map {
                        intent.extras?.get(it.toString()).toString()
                    }.toString(), resultCode)
            }
        }
    }


}