package com.example.core.databases.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDB(
    @PrimaryKey(autoGenerate = true) val userId: Long = 0,
    @ColumnInfo(name = "name") val name: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDB

        if (userId != other.userId) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
