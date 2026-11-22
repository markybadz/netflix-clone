package com.netflixclone.ui

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

/** True on Android TV / Google TV devices (for example TCL TVs). */
fun Context.isTelevision(): Boolean =
    (resources.configuration.uiMode and Configuration.UI_MODE_TYPE_MASK) ==
            Configuration.UI_MODE_TYPE_TELEVISION

@Composable
fun isTelevision(): Boolean {
    val config = LocalConfiguration.current
    return (config.uiMode and Configuration.UI_MODE_TYPE_MASK) ==
            Configuration.UI_MODE_TYPE_TELEVISION
}

/**
 * Draws a white outline while the element (or the clickable placed after this
 * modifier) has remote / D-pad focus, so TV users can see where they are.
 * Must be placed before `clickable` in the modifier chain.
 */
fun Modifier.focusOutline(shape: Shape = RectangleShape): Modifier = composed {
    var focused by remember { mutableStateOf(false) }
    this
        .onFocusChanged { focused = it.hasFocus }
        .border(
            width = if (focused) 3.dp else 0.dp,
            color = if (focused) Color.White else Color.Transparent,
            shape = shape
        )
}
