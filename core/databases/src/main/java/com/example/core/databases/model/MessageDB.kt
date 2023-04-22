package com.example.core.databases.model

import androidx.room.Entity

@Entity(tableName = "messages", primaryKeys = ["senderId", "receiverId"])
data class MessageDB(
    val senderId: Long,
    val receiverId: Long,
    val text: String
)