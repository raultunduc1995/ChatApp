package com.example.feature.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.chat.publ.model.LoadingMessage
import com.example.data.chat.publ.model.Message
import com.example.data.chat.publ.model.MessageTemplate
import com.example.data.chat.publ.model.User
import com.example.data.chat.publ.repository.MessageRepository
import com.example.data.chat.publ.repository.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

data class ConversationUiState(
    val messages: List<MessageTemplate> = emptyList(),
    val receiver: User = User(name = "")
)

class ConversationViewModel(
    friendUserId: Long,
    private val userRepository: UserRepository,
    private val messageRepository: MessageRepository,
) : ViewModel() {
    private val _simulateFriendResponse = AtomicBoolean(false)

    init {
        viewModelScope.launch {
            messageRepository
                .getMessagesBetween(firstUserId = User.MAIN_USER.id, secondUserId = friendUserId)
                .catch { }
                .collect { messages ->
                    viewModelScope.launch {
                        _uiState.update { state ->
                            state.copy(
                                messages = if (_simulateFriendResponse.get()) {
                                    messages.plus(
                                        LoadingMessage(
                                            sender = state.receiver,
                                            receiver = User.MAIN_USER
                                        )
                                    )
                                } else {
                                    messages
                                }
                            )
                        }
                    }
                }
        }
        viewModelScope.launch {
            userRepository.getUserById(userId = friendUserId)
                .catch { }
                .collect { user ->
                    _uiState.update { it.copy(receiver = user) }
                }
        }
    }

    private val _uiState = MutableStateFlow(ConversationUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    fun sendMessageTo(receiver: User, text: String) {
        if (text.isEmpty()) return

        viewModelScope.launch {
            kotlin.runCatching {
                messageRepository.insert(
                    message = Message(
                        sender = User.MAIN_USER,
                        receiver = receiver,
                        text = text
                    )
                )
                job?.cancelAndJoin()
                job = simulateFriendResponse()
            }
        }
    }

    private suspend fun simulateFriendResponse() = viewModelScope.launch {
        delay(1_000)
        _simulateFriendResponse.set(true)
        _uiState.update { state ->
            state.copy(
                messages = state.messages
                    .filterNot { it is LoadingMessage }
                    .plus(LoadingMessage(sender = state.receiver, receiver = User.MAIN_USER))
            )
        }

        delay(8_000)
        _simulateFriendResponse.set(false)

        delay(1_000)
        kotlin.runCatching {
            messageRepository.insert(
                message = Message(
                    sender = _uiState.value.receiver,
                    receiver = User.MAIN_USER,
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas maximus accumsan neque, pellentesque consectetur sem pellentesque eu. Nunc a hendrerit mi, eget sollicitudin nibh. In porttitor lacus sed augue pretium rutrum."
                )
            )
        }
    }
}
