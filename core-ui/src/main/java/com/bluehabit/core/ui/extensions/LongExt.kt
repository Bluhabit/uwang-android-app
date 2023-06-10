/*
 * Copyright (c) 2023 trian.app.
 *
 *  Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *
 */

package app.hilwa.ar.base.extensions

import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.MINUTES

fun Long.formatTimer() = String.format(
    "%02d : %02d : %02d ",
    MILLISECONDS.toHours(this),
    MILLISECONDS.toMinutes(this),
    MILLISECONDS.toSeconds(this) -
            MINUTES.toSeconds(MILLISECONDS.toMinutes(this))
)