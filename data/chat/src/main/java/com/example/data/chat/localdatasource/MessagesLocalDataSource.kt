package com.example.data.chat.localdatasource

import com.example.core.databases.dao.MessageDBDao
import com.example.core.databases.model.MessageDB
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.withContext

class MessagesLocalDataSource(
    private val messageDBDao: MessageDBDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insert(vararg messagesDB: MessageDB) = withContext(ioDispatcher) {
        messageDBDao.insertAll(*messagesDB)
    }

    fun getMessagesBetween(firstUserId: Long, secondUserId: Long): Flow<List<MessageDB>> =
        messageDBDao.getMessagesBetween(firstUser = firstUserId, secondUser = secondUserId)
            .distinctUntilChanged()
}