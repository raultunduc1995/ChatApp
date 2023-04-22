package com.example.feature.chat.ui.screens

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.feature.chat.ui.theme.ChatAppTheme
import com.example.feature.chat.ui.utils.WhiteBackgroundPreview
import com.example.feature.chat.viewmodel.HomeViewModel

@Composable
fun ConversationScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    userId: Long
) {
    Conversation(userId = userId, modifier = Modifier)
}

@Composable
fun Conversation(userId: Long, modifier: Modifier) {
    Text(text = "$userId")
}

@WhiteBackgroundPreview
@Composable
fun ConversationPreview() {
    ChatAppTheme {
        Surface {
            Conversation(userId = 12, modifier = Modifier)
        }
    }
}