package com.example.feature.chat.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.chat.publ.model.User
import com.example.feature.chat.ui.components.CircleUserAvatar
import com.example.feature.chat.ui.navigation.ChatAppScreen
import com.example.feature.chat.ui.theme.ChatAppTheme
import com.example.feature.chat.ui.utils.WhiteBackgroundPreview
import com.example.feature.chat.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val userFriends by viewModel.userFriends.collectAsState(initial = emptyList())
    val showTextFieldTransitionState = remember { MutableTransitionState(false) }
    val focusRequester = remember { FocusRequester() }
    var textFieldText by rememberSaveable { mutableStateOf<String>("") }

    if (showTextFieldTransitionState.isIdle && showTextFieldTransitionState.currentState) {
        LaunchedEffect(showTextFieldTransitionState) {
            focusRequester.requestFocus()
        }
    }

    Home(
        focusRequester = focusRequester,
        users = userFriends,
        showTextFieldTransitionState = showTextFieldTransitionState,
        textFieldText = textFieldText,
        toggleTextFieldVisibility = {
            showTextFieldTransitionState.targetState = it
        },
        updateTextFieldText = {
            textFieldText = it
        },
        insertFriend = {
            viewModel.insertNewFriend(friendName = it)
        },
        navigateToFriendConversation = { user ->
            navController.navigate("${ChatAppScreen.Conversation.title}/${user.id}?userName=${user.name}")
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    users: List<User>,
    showTextFieldTransitionState: MutableTransitionState<Boolean>,
    textFieldText: String,
    toggleTextFieldVisibility: (Boolean) -> Unit,
    updateTextFieldText: (String) -> Unit,
    insertFriend: (String) -> Unit,
    navigateToFriendConversation: (User) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    toggleTextFieldVisibility(true)
                },
                shape = CircleShape,
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            AnimatedVisibility(visibleState = showTextFieldTransitionState) {
                TextField(
                    value = textFieldText,
                    onValueChange = { text ->
                        updateTextFieldText(text.trimStart { it == '0' })
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    label = { Text(text = "Name:") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            toggleTextFieldVisibility(false)
                            insertFriend(textFieldText)
                            updateTextFieldText("")
                        }
                    ),
                    singleLine = true,
                )
            }
            LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                items(users) { user ->
                    FriendListItem(
                        user = user,
                        onRowClicked = navigateToFriendConversation
                    )
                }
            }
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
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleUserAvatar(
            modifier = Modifier.size(60.dp),
            avatarColor = Color.Cyan,
            textStyle = MaterialTheme.typography.headlineLarge,
            textColor = Color.White,
            user = user
        )
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
    val showTextFieldTransitionState = remember { MutableTransitionState(true) }
    val focusRequester = remember { FocusRequester() }

    ChatAppTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Home(
                users = listOf(
                    User(1, "Main User"),
                    User(2, "Second user")
                ),
                focusRequester = focusRequester,
                showTextFieldTransitionState = showTextFieldTransitionState,
                textFieldText = "Text field text",
                toggleTextFieldVisibility = {},
                updateTextFieldText = {},
                insertFriend = {},
                navigateToFriendConversation = {},
            )
        }
    }
}