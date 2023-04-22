package com.example.feature.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.chat.publ.model.Message
import com.example.data.chat.publ.model.User
import com.example.data.chat.publ.repository.MessageRepository
import com.example.data.chat.publ.repository.UserRepository
import kotlinx.coroutines.launch

class ConversationViewModel(
    friendUserId: Long,
    private val userRepository: UserRepository,
    private val messageRepository: MessageRepository,
) : ViewModel() {
    val messages = messageRepository.getMessagesBetween(
        firstUserId = User.MAIN_USER.id,
        secondUserId = friendUserId
    )
    val receiver = userRepository.getUserById(userId = friendUserId)

    fun sendMessageTo(receiver: User, text: String) = viewModelScope.launch {
        if (text.isEmpty()) return@launch
        val message = Message(
            sender = User.MAIN_USER,
            receiver = receiver,
            text = text
        )
        kotlin.runCatching {
            messageRepository.insert(message)
        }
    }
}
