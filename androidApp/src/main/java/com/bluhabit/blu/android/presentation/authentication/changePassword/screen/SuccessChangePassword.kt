
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun ScreenCompleteForgotPassword(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit = {}
) {
    LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.surface
            )
            .padding(
                vertical = 20.dp,
                horizontal = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = modifier.height(200.dp))
            Image(
                painter = painterResource(id = R.drawable.succeschanges),
                contentDescription = ""
            )
            Spacer(modifier = modifier.height(50.dp))
            Text(
                text = stringResource(id = R.string.text_label_forgot_password),
                style = MaterialTheme.typography.body1,
                fontSize = 20.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF0E0F0C),
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.text_subtitle_forgot_password),
                style = MaterialTheme.typography.body1,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF4F504E),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.height(250.dp))
            ButtonPrimary(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(R.string.text_button_forgot_password),
                onClick = onConfirm
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewCompleteResetPasswordScreen() {
    UwangTheme {
        ScreenCompleteForgotPassword()
    }
}