package com.example.core.databases.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageDB(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val senderId: Long,
    val receiverId: Long,
    val text: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MessageDB

        if (id != other.id) return false
        if (senderId != other.senderId) return false
        if (receiverId != other.receiverId) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}