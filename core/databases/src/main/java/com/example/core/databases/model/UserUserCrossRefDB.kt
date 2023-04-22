package com.example.core.databases.model

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "friendId"], tableName = "user_user_cross_ref")
data class UserUserCrossRefDB(
    val userId: Long,
    val friendId: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserUserCrossRefDB

        if (userId != other.userId) return false
        if (friendId != other.friendId) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}