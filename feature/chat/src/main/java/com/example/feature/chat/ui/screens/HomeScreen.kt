package com.example.feature.chat.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.feature.chat.ui.theme.ChatAppTheme
import com.example.feature.chat.ui.utils.WhiteBackgroundPreview

@Composable
fun HomeScreen(navController: NavController) {
    Home(
        onNavigateToFriendConversation = { userName ->
            navController.navigate("conversation/$userName")
        },
        onNavigateUp = {
            navController.navigateUp()
        },
        modifier = Modifier
    )
}

@Composable
fun Home(
    onNavigateToFriendConversation: (String) -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier
) {
    Text(
        text = "Hello Android!",
        modifier = modifier
    )
}

@WhiteBackgroundPreview
@Composable
fun HomePreview() {
    ChatAppTheme {
        Home(
            onNavigateToFriendConversation = {},
            onNavigateUp = {},
            modifier = Modifier
        )
    }
}