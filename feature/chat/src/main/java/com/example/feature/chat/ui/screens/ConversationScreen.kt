package com.example.feature.chat.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.feature.chat.ui.theme.ChatAppTheme
import com.example.feature.chat.ui.utils.WhiteBackgroundPreview

@Composable
fun ConversationScreen(
    navController: NavController,
    userName: String
) {
    Conversation(userName = userName, modifier = Modifier)
}

@Composable
fun Conversation(userName: String, modifier: Modifier) {
}

@WhiteBackgroundPreview
@Composable
fun ConversationPreview() {
    ChatAppTheme {
        Conversation(userName = "", modifier = Modifier)
    }
}