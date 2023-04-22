package com.example.core.databases.model

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "friendId"], tableName = "user_user_cross_ref")
data class UserUserCrossRefDB(
    val userId: Long,
    val friendId: Long
)