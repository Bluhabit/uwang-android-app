/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.AnnotationTextItem
import com.bluehabit.budgetku.android.components.TextWithAction

@Composable
internal fun ScreenSignUp(
    modifier: Modifier = Modifier,
    onSubmit:(String,String,String)->Unit={_,_,_->},
    onSubmitWithGoogle:()->Unit ={},
    onSignIn:()->Unit={}
) {
    var fullName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                horizontal = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            TextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                },
                placeholder={
                    Text(text = "Full Name")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                placeholder={
                    Text(text = "Email")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder={
                    Text(text = "Password")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column {
            Button(
                onClick = { onSubmit(email, password,fullName) },
                modifier=Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign In")
            }

            Button(
                onClick = { onSubmitWithGoogle() },
                modifier=Modifier.fillMaxWidth()
            ) {
                Text(text = "Continue With Google")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextWithAction(
            labels = listOf(
                AnnotationTextItem.Text("Sudah punya akun?"),
                AnnotationTextItem.Button("Masuk disni")
            ),
            onTextClick = {
                if(it == 1){
                    onSignIn()
                }
            }
        )

    }
}

@Preview
@Composable
fun PreviewScreenSignUp() {
    BaseMainApp {
        ScreenSignUp()
    }

}