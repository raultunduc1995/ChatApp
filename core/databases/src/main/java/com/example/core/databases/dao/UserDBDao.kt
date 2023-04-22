package com.example.core.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.databases.model.UserDB
import com.example.core.databases.model.UserUserCrossRefDB
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDBDao: UserDB): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg userUserCrossRefDB: UserUserCrossRefDB)

    @Query("""SELECT * FROM users""")
    fun getAll(): Flow<List<UserDB>>

    @Transaction
    @Query(
        """
            SELECT users.userId, users.name FROM users
            INNER JOIN user_user_cross_ref ON user_user_cross_ref.friendId = users.userId 
            WHERE user_user_cross_ref.userId = :userId
        """
    )
    fun getUserFriends(userId: Long): Flow<List<UserDB>>

    @Query(
        """
            SELECT * FROM users
            WHERE userId = :userId
        """
    )
    suspend fun getUserById(userId: Long): UserDB

    @Query(
        """
            SELECT * FROM users
            WHERE userId = :userId
        """
    )
    fun getUserByIdFlow(userId: Long): Flow<UserDB>
}