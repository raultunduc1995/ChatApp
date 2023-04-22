package com.example.data.chat.publ.repository

import com.example.core.databases.model.MessageDB
import com.example.data.chat.localdatasource.MessagesLocalDataSource
import com.example.data.chat.publ.model.Message
import com.example.data.chat.publ.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MessageRepository(
    private val messagesLocalDataSource: MessagesLocalDataSource,
    private val ioDispather: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insert(message: Message) = withContext(ioDispather) {
        messagesLocalDataSource.insert(message.mapToMessageDB())
    }

    suspend fun insert(messages: List<Message>) = withContext(ioDispather) {
        val messageDBs = messages.map(Message::mapToMessageDB).toTypedArray()
        messagesLocalDataSource.insert(*messageDBs)
    }

    fun getMessagesBetween(firstUser: User, secondUser: User) =
        messagesLocalDataSource.getMessagesBetween(
            firstUserId = firstUser.id,
            secondUserId = secondUser.id
        )
            .map { messageDBs ->
                messageDBs.map { it.mapToMessage(firstUser, secondUser) }
            }
}

private fun Message.mapToMessageDB(): MessageDB =
    MessageDB(
        senderId = sender.id,
        receiverId = receiver.id,
        text = text
    )

private fun MessageDB.mapToMessage(firstUser: User, secondUser: User): Message =
    Message(
        sender = if (firstUser.id == senderId) firstUser else secondUser,
        receiver = if (firstUser.id == receiverId) firstUser else secondUser,
        text = text
    )