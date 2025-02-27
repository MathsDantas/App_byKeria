package com.example.bykeria.ui.components

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.bykeria.ui.theme.YourAppTheme

@Composable
fun AppWithThemeToggle() {
    var isDarkTheme by remember { mutableStateOf(false) }

    YourAppTheme(darkTheme = isDarkTheme) {

        Switch(
            checked = isDarkTheme,
            onCheckedChange = { isDarkTheme = it }
        )

    }
}
