/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.editProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.extensions.from
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.input.InputTextPrimary
import com.bluehabit.core.ui.components.sheet.EditPhotoBottomSheet
import com.bluehabit.eureka.feature.authentication.R
import kotlinx.coroutines.launch


@Composable
fun EditProfileScreen(
    uiContract: UIContract<EditProfileState,EditProfileAction>
) = UIWrapper(uiContract = uiContract) {
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false,

        )
    val context = LocalContext.current
    val profilePicture = rememberAsyncImagePainter(
        model =ImageRequest
            .Builder(context)
            .data(state.imageUrl)
            .placeholder(com.bluehabit.eureka.data.R.drawable.dummy_avatar_1)
            .build()
    )
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = { EditPhotoBottomSheet() },
        sheetState = modalSheetState,
        sheetBackgroundColor = Color.White,
        scrimColor = Color(0x1A0D0D0D),
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
    ) {
        Scaffold(
            topBar = {
                TopAppBar(

                    title = {
                        Text(text = "Edit Profile" )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(painter = painterResource(
                                id = com.bluehabit.core.ui.R.drawable.ic_arrow_back),
                                contentDescription = "Arrow Back" )
                        }
                    }
                )
            }
        ){
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(
                        horizontal = 20.dp,
                        vertical = 24.dp
                    )
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(72.dp)
                        .height(72.dp)
                        .clickable(onClick = {scope.launch{modalSheetState.show()}})
                ) {
                    Image(
                        modifier = Modifier
                            .width(72.dp.from(context = context))
                            .height(72.dp.from(context = context))
                            .align(Alignment.Center)
                            .clip(CircleShape),
                        painter = profilePicture,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "Profile Picture",
                    )
                    Box(
                        modifier = Modifier
                            .width(24.dp.from(context = context))
                            .height(24.dp.from(context = context))
                            .padding(1.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .align(Alignment.BottomEnd)
                    ) {
                        Image(
                            modifier = Modifier
                                .width(16.dp.from(context = context))
                                .height(16.dp.from(context = context))
                                .align(Alignment.Center),
                            painter = painterResource(id = com.bluehabit.core.ui.R.drawable.pencil_square),
                            contentDescription = "",
                            contentScale = ContentScale.None
                        )
                    }
                }

                InputTextPrimary(
                    label = "Nama Pengguna",
                    placeholder = "Masukan Nama Penggunaan",

                )
                ButtonPrimary(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Simpan Perubahan",

                )
            }
        }

    }
}
@Composable
@Preview
fun PreviewEditProfile(){
    var state by remember {
        mutableStateOf(EditProfileState())
    }
    EditProfileScreen(
        uiContract = UIContract(
            controller =  rememberUIController(),
            state = state,
            mutation ={
                state = it
            }
        )
    )
}