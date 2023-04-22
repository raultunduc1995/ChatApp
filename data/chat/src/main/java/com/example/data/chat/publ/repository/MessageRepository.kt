package com.example.data.chat.publ.repository

import com.example.data.chat.localdatasource.MessagesLocalDataSource
import com.example.data.chat.localdatasource.UserLocalDataSource
import com.example.data.chat.publ.model.Message
import com.example.data.chat.publ.utils.mapToMessage
import com.example.data.chat.publ.utils.mapToMessageDB
import com.example.data.chat.publ.utils.mapToUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext

class MessageRepository(
    private val userLocalDataSource: UserLocalDataSource,
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

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getMessagesBetween(firstUserId: Long, secondUserId: Long): Flow<List<Message>> {
        return messagesLocalDataSource.getMessagesBetween(
            firstUserId = firstUserId,
            secondUserId = secondUserId
        )
            .mapLatest { mesageDBs ->
                val firstUser = userLocalDataSource.getUserById(firstUserId).mapToUser()
                val secondUser = userLocalDataSource.getUserById(secondUserId).mapToUser()
                mesageDBs.map {
                    it.mapToMessage(firstUser = firstUser, secondUser = secondUser)
                }
            }
    }
}

