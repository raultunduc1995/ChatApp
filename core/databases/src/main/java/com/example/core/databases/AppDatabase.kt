package com.example.core.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.databases.dao.MessageDBDao
import com.example.core.databases.dao.UserDBDao
import com.example.core.databases.model.MessageDB
import com.example.core.databases.model.UserDB
import com.example.core.databases.model.UserUserCrossRefDB

@Database(
    entities = [
        UserDB::class,
        UserUserCrossRefDB::class,
        MessageDB::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDBDao(): UserDBDao
    abstract fun messageDBDao(): MessageDBDao
}