/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.sheet

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.theme.Gray100
import com.bluhabit.core.ui.theme.Inter
import com.bluhabit.core.ui.theme.Neutral100
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDate
import kotlinx.coroutines.launch

@Composable
fun DatePickerBottomSheet(
    title: String = String.Empty,
    minDate: LocalDate = LocalDate.now(),
    onDone: () -> Unit = {},
    onChange: (date: LocalDate) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 24.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "close"
            )
            Text(
                text = title,
                lineHeight = 28.sp,
                fontWeight = FontWeight.W600,
                color = Neutral100,
                style = MaterialTheme.typography.subtitle1
            )
        }
        WheelDatePicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            minDate = minDate,
            size = DpSize(350.dp, 200.dp),
            rowCount = 5,
            textStyle = TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 0.15.sp
            ),
            selectorProperties = WheelPickerDefaults.selectorProperties(
                enabled = true,
                shape = RoundedCornerShape(0.dp),
                color = Gray100,
                border = BorderStroke(0.dp, Gray100)
            ),
            onSnappedDate = { snappedDate ->
                onChange.invoke(snappedDate)
            }
        )
        ButtonPrimary(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.btn_text_choose),
            onClick = onDone
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview(widthDp = 500, heightDp = 750, backgroundColor = 0xffffff)
fun DatePickerBottomSheetPreview() {
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false,

        )
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetContent = {
            DatePickerBottomSheet()
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0x1A0D0D0D)),
        ) {
            Button(onClick = { scope.launch { modalSheetState.show() } }) {
                Text("Click to show sheet")
            }
        }

    }
}