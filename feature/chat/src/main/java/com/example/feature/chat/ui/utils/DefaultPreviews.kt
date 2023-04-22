package com.example.feature.chat.ui.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light mode",
    showSystemUi = true,
    showBackground = true,
)
@Preview(
    name = "Dark mode",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class WhiteBackgroundPreview