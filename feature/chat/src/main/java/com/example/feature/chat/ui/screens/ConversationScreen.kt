package com.example.feature.chat.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.chat.publ.model.Message
import com.example.data.chat.publ.model.User
import com.example.feature.chat.ui.components.CircleUserAvatar
import com.example.feature.chat.ui.theme.ChatAppTheme
import com.example.feature.chat.ui.utils.WhiteBackgroundPreview
import com.example.feature.chat.viewmodel.ConversationViewModel
import kotlinx.coroutines.delay

@Composable
fun ConversationScreen(
    navController: NavController,
    viewModel: ConversationViewModel,
) {
    val messages by viewModel.messages.collectAsState(initial = emptyList())
    val receiver by viewModel.receiver.collectAsState(initial = User(name = ""))

    Conversation(
        messages = messages,
        receiver = receiver,
        sendMessageTo = viewModel::sendMessageTo
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Conversation(
    modifier: Modifier = Modifier,
    messages: List<Message>,
    receiver: User,
    sendMessageTo: (User, String) -> Unit
) {
    var textFieldText by rememberSaveable { mutableStateOf("") }
    val listState = rememberLazyListState()

    if (messages.isNotEmpty()) {
        LaunchedEffect(key1 = messages.size) {
            listState.animateScrollToItem(index = messages.lastIndex)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f, fill = true),
            state = listState,
            contentPadding = PaddingValues(vertical = 6.dp, horizontal = 8.dp)
        ) {
            items(messages) {
                TextMessage(message = it)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = textFieldText,
                onValueChange = { text ->
                    textFieldText = text.trimStart { it == '0' }
                },
                modifier = Modifier.weight(1f, fill = true),
                placeholder = { Text("Aa") },
                shape = RoundedCornerShape(32.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = {
                    sendMessageTo(receiver, textFieldText)
                    textFieldText = ""
                }
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun TextMessage(
    modifier: Modifier = Modifier,
    message: Message
) {
    if (message.isMainUserTheSender()) {
        RightTextMessage(modifier = modifier, message = message)
    } else {
        LeftTextMessage(modifier = modifier, message = message)
    }

}

@Composable
fun RightTextMessage(
    modifier: Modifier,
    message: Message
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        MessageContent(
            modifier = Modifier.weight(1f, fill = false),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
            messageText = message.text
        )
        Spacer(modifier = Modifier.width(8.dp))
        CircleUserAvatar(
            modifier = Modifier.requiredSize(45.dp),
            avatarColor = MaterialTheme.colorScheme.tertiaryContainer,
            textStyle = MaterialTheme.typography.bodyLarge,
            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
            user = message.sender
        )
    }
}

@Composable
fun LeftTextMessage(
    modifier: Modifier,
    message: Message
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        CircleUserAvatar(
            modifier = Modifier.requiredSize(45.dp),
            avatarColor = MaterialTheme.colorScheme.secondaryContainer,
            textStyle = MaterialTheme.typography.bodyLarge,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
            user = message.sender
        )
        Spacer(modifier = Modifier.width(8.dp))
        MessageContent(
            modifier = Modifier.weight(1f, fill = false),
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
            messageText = message.text
        )
    }
}

@Composable
fun MessageContent(
    modifier: Modifier,
    containerColor: Color,
    textColor: Color,
    messageText: String
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = containerColor,
        shadowElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)) {
            Text(
                text = messageText,
                textAlign = TextAlign.Start,
                color = textColor,
                maxLines = Int.MAX_VALUE
            )
        }
    }
}


@WhiteBackgroundPreview
@Composable
fun ConversationPreview() {
    val firstUser = User(id = 1, name = "Main User")
    val secondUser = User(id = 2, name = "Paul User")
    val messages = listOf(
        Message(
            sender = secondUser,
            receiver = firstUser,
            text = "Hello Main."
        ),
        Message(
            sender = secondUser,
            receiver = firstUser,
            text = "How are you? I was wonderying if we can go to the cinema tonight."
        ),
        Message(
            sender = firstUser,
            receiver = secondUser,
            text = "Hello Paul."
        ),
        Message(
            sender = firstUser,
            receiver = secondUser,
            text = "Sure. No problem."
        ),
        Message(
            sender = firstUser,
            receiver = secondUser,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas maximus accumsan neque, pellentesque consectetur sem pellentesque eu. Nunc a hendrerit mi, eget sollicitudin nibh. In porttitor lacus sed augue pretium rutrum. "
        ),
    )

    ChatAppTheme {
        Surface {
            Conversation(
                messages = messages,
                receiver = User(name = "Receiver"),
                sendMessageTo = { _, _ -> }
            )
        }
    }
}