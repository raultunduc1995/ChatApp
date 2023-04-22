package com.example.feature.chat.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.chat.publ.model.User
import com.example.feature.chat.ui.theme.ChatAppTheme
import com.example.feature.chat.ui.utils.WhiteBackgroundPreview
import com.example.feature.chat.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val userFriends by viewModel.userFriends.collectAsState(initial = emptyList())
    Home(
        users = userFriends,
        navigateToFriendConversation = { user ->
            navController.navigate("conversation/${user.id}?userName=${user.name}")
        },
        onNavigateUp = {
            navController.navigateUp()
        },
    )
}

@Composable
fun Home(
    modifier: Modifier = Modifier,
    users: List<User>,
    navigateToFriendConversation: (User) -> Unit,
    onNavigateUp: () -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(users) {
            FriendListItem(
                user = it,
                onRowClicked = navigateToFriendConversation
            )
        }
    }
}

@Composable
fun FriendListItem(
    modifier: Modifier = Modifier,
    user: User,
    onRowClicked: (User) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onRowClicked(user)
            }
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            color = Color.Cyan,
            shadowElevation = 4.dp
        ) {
            Text(
                text = user.name.first().uppercase(),
                modifier = Modifier
                    .size(60.dp)
                    .wrapContentHeight(),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Text(
            text = user.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@WhiteBackgroundPreview
@Composable
fun HomePreview() {
    ChatAppTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Home(
                users = listOf(
                    User(1, "Main User"),
                    User(2, "Second user")
                ),
                navigateToFriendConversation = {},
                onNavigateUp = {},
            )
        }
    }
}