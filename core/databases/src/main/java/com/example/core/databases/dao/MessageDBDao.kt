package com.example.core.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.databases.model.MessageDB
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg messageDBDao: MessageDB)

    @Query(
        """
        SELECT * FROM messages
        WHERE (senderId = :firstUser AND receiverId = :secondUser)
            OR (senderId = :secondUser AND receiverId = :firstUser)"""
    )
    fun getMessagesBetween(firstUser: Long, secondUser: Long): Flow<List<MessageDB>>
}