/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun DialogComponent(isSuccess: Boolean = true, label: String, message: String, btnText: String) {
    Card(
        modifier = Modifier
            .width(328.dp)
            .height(351.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(8.dp))
            .padding(start = 26.dp, top = 28.dp,end = 26.dp , bottom = 28.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(35.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isSuccess) {

                Image(
                    painter = painterResource(id = R.drawable.success_icon_purple),
                    contentDescription = "succesIcon",
                    modifier = Modifier
                        .padding(1.dp)
                        .width(103.dp)
                        .height(103.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.success_icon_purple),
                    contentDescription = "failedIcon",
                    modifier = Modifier
                        .padding(1.dp)
                        .width(103.dp)
                        .height(103.dp)
                )
            }
            Column(

                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Text(
                    text = label, style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 30.sp,
                        fontFamily = FontFamily(Font(R.font.inter_semi_bold)),
                        color = Gray900,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = message, style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        color = Gray900,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .width(276.dp)
                        .height(40.dp)
                )
            }
            Button(onClick = { /*TODO*/ }) {
                Text(
                    text = btnText, style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.inter_semi_bold)),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

    }
}

@Composable
@Preview
fun DefaultPreview() {
    DialogComponent(label = "Berhasil Daftar", message = "Tinggal 1 langkah lagi untuk melengkapi profile Anda.", btnText = "Lanjut")
}