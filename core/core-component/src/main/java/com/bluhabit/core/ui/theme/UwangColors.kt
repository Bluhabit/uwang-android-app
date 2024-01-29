/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.theme

import androidx.compose.ui.graphics.Color

object UwangColors {
    object Primary {
        val Blue50 = Color(0xFFE7ECF8)
        val Blue100 = Color(0xFFB5C4E9)
        val Blue200 = Color(0xFF91A8DE)
        val Blue300 = Color(0xFF5F80CF)
        val Blue400 = Color(0xFF4067C5)
        val Blue500 = Color(0xFF1041B7)
        val Blue600 = Color(0xFF0F3BA7)
        val Blue700 = Color(0xFF0B2E82)
        val Blue800 = Color(0xFF092465)
        val Blue900 = Color(0xFF071B4D)
        val Blue1000 = Color(0xFF1152ED)
    }

    object Neutral {
        val Grey1 = Color(0xFFFFFFFF)
        val Grey2 = Color(0xFFFDFDFD)
        val Grey3 = Color(0xFFF0F0F0)
        val grey4 = Color(0xFFF1F1F0)
        val Grey5 = Color(0xFFDBDBDB)
        val Grey6 = Color(0xFFC3C3C2)
        val Grey7 = Color(0xFF939392)
        val Grey8 = Color(0xFF626361)
        val Grey9 = Color(0xFF4F504E)
        val Grey10 = Color(0xFF323330)
        val Grey11 = Color(0xFF2B2C29)
        val Grey12 = Color(0xFF21221F)
        val Grey13 = Color(0xFF0E0F0C)
    }

    object Error {
        val Red50 = Color(0xFFFCEAEA)
        val Red100 = Color(0xFFF4BEBE)
        val Red200 = Color(0xFFEF9F9F)
        val Red300 = Color(0xFFE87373)
        val Red400 = Color(0xFFE45858)
        val Red500 = Color(0xFFDD2E2E)
        val Red600 = Color(0xFFC92A2A)
        val Red700 = Color(0xFF9D2121)
        val Red800 = Color(0xFF7A1919)
        val Red900 = Color(0xFF5D1313)
    }

    object Warning {
        val Yellow50 = Color(0xFFFEFAEA)
        val Yellow100 = Color(0xFFFCF0BF)
        val Yellow200 = Color(0xFFFAE9A0)
        val Yellow300 = Color(0xFFF8E074)
        val Yellow400 = Color(0xFFF6D959)
        val Yellow500 = Color(0xFFF4D030)
        val Yellow600 = Color(0xFFDEBD2C)
        val Yellow700 = Color(0xFFAD9422)
        val Yellow800 = Color(0xFF86721A)
        val Yellow900 = Color(0xFF665714)
    }

    object Success {
        val Green50 = Color(0xFFE8F5EF)
        val Green100 = Color(0xFFB7E1CD)
        val Green200 = Color(0xFF94D2B5)
        val Green300 = Color(0xFF64BD93)
        val Green400 = Color(0xFF45B17E)
        val Green500 = Color(0xFF179D5E)
        val Green600 = Color(0xFF158F56)
        val Green700 = Color(0xFF106F43)
        val Green800 = Color(0xFF0D5634)
        val Green900 = Color(0xFF0A4227)
    }

    object Text {
        val Heading = Palette.Neutral.Grey11
        val Main = Palette.Neutral.Grey9
        val Secondary = Palette.Neutral.Grey7
        val Disabled = Palette.Neutral.Grey5
        val Border = Palette.Neutral.Grey5
        val Separator = Palette.Neutral.Grey4
        val LayoutBackground = Palette.Neutral.Grey2
    }

    object State {
        object Primary {
            val Main = Palette.Primary.Blue6
            val Surface = Palette.Primary.Blue1
            val Border = Palette.Primary.Blue1
            val Hover = Palette.Primary.Blue7
            val Pressed = Palette.Primary.Blue9
            val Focus = Palette.Primary.Blue6.copy(alpha = 0.20f)
        }

        object Neutral {
            val Heading = Palette.Neutral.Grey11
            val Main = Palette.Neutral.Grey9
            val Secondary = Palette.Neutral.Grey7
            val Disabled = Palette.Neutral.Grey5
            val Border = Palette.Neutral.Grey5
            val Separator = Palette.Neutral.Grey4
            val LayoutBackground = Palette.Neutral.Grey2
        }

        object Error {
            val Main = Palette.Error.Red6
            val Surface = Palette.Error.Red1
            val Border = Palette.Error.Red1
            val Hover = Palette.Error.Red7
            val Pressed = Palette.Error.Red9
            val Focus = Palette.Error.Red6.copy(alpha = 0.20f)
        }

        object Warning {
            val Main = Palette.Warning.Yellow6
            val Surface = Palette.Warning.Yellow1
            val Border = Palette.Warning.Yellow2
            val Hover = Palette.Warning.Yellow7
            val Pressed = Palette.Warning.Yellow9
            val Focus = Palette.Warning.Yellow6.copy(alpha = 0.20f)
        }

        object Success {
            // New Update
            val Main = Palette.Success.Green6
            val Surface = Palette.Success.Green1
            val Border = Palette.Success.Green2
            val Hover = Palette.Success.Green7
            val Pressed = Palette.Success.Green9
            val Focus = Palette.Success.Green6.copy(alpha = 0.20f)
        }
    }

    object Palette {
        object Primary {
            val Blue1 = Color(0xFFe7ecf8)
            val Blue2 = Color(0xFFc6d1ee)
            val Blue3 = Color(0xFF98ade0)
            val Blue4 = Color(0xFF6887d2)
            val Blue5 = Color(0xFF3b63c4)
            val Blue6 = Color(0xFF1041b7)
            val Blue7 = Color(0xFF0e379c)
            val Blue8 = Color(0xFF0b2e82)
            val Blue9 = Color(0xFF092568)
            val Blue10 = Color(0xFF071d52)
        }

        object Neutral {
            val Grey1 = Color(0xFFfcfcfd)
            val Grey2 = Color(0xFFf9fafb)
            val Grey3 = Color(0xFFf2f4f7)
            val Grey4 = Color(0xFFeaecf0)
            val Grey5 = Color(0xFFd0d5dd)
            val Grey6 = Color(0xFF98a2b3)
            val Grey7 = Color(0xFF667085)
            val Grey8 = Color(0xFF475467)
            val Grey9 = Color(0xFF344054)
            val Grey10 = Color(0xFF1d2939)
            val Grey11 = Color(0xFF101828)
        }

        object Error {
            val Red1 = Color(0xFFfceaea)
            val Red2 = Color(0xFFf7cdcd)
            val Red3 = Color(0xFFf0a5a5)
            val Red4 = Color(0xFFea7b7b)
            val Red5 = Color(0xFFe35454)
            val Red6 = Color(0xFFdd2e2e)
            val Red7 = Color(0xFFbc2727)
            val Red8 = Color(0xFF9d2121)
            val Red9 = Color(0xFF7e1a1a)
            val Red10 = Color(0xFF631515)
        }

        object Warning {
            val Yellow1 = Color(0xFFfff7e8)
            val Yellow2 = Color(0xFFfcf4cd)
            val Yellow3 = Color(0xFFfaeba6)
            val Yellow4 = Color(0xFFf8e17d)
            val Yellow5 = Color(0xFFf6d855)
            val Yellow6 = Color(0xFFf4d030)
            val Yellow7 = Color(0xFFcfb129)
            val Yellow8 = Color(0xFFad9422)
            val Yellow9 = Color(0xFF8b771b)
            val Yellow10 = Color(0xFF6e5e16)
        }

        object Success {
            val Green1 = Color(0xFFe8f5ef)
            val Green2 = Color(0xFFc7e7d8)
            val Green3 = Color(0xFF9bd5ba)
            val Green4 = Color(0xFF6dc19a)
            val Green5 = Color(0xFF41af7b)
            val Green6 = Color(0xFF179d5e)
            val Green7 = Color(0xFF148550)
            val Green8 = Color(0xFF106f43)
            val Green9 = Color(0xFF106f43)
            val Green10 = Color(0xFF0a472a)
        }
    }

    object Base {
        val White = Color.White
        val Black = Color.Black
    }
}