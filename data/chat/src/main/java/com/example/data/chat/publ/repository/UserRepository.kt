package com.example.data.chat.publ.repository

import com.example.core.databases.model.UserDB
import com.example.core.databases.model.UserUserCrossRefDB
import com.example.data.chat.localdatasource.UserLocalDataSource
import com.example.data.chat.publ.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserRepository(
    private val userLocalDataSource: UserLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    companion object {
        val MAIN_USER = User(id = 1, name = "Main User")
    }

    suspend fun insertUserComplete(user: User) = withContext(ioDispatcher) {
        userLocalDataSource.insert(user.mapToUserDB())
    }

    suspend fun insertUserFriend(user: User) = withContext(ioDispatcher) {
        val insertedUserId = userLocalDataSource.insert(user.mapToUserDBOnlyName())
        val savedUser = userLocalDataSource.getUserById(insertedUserId)
        userLocalDataSource.insertUserFriend(
            UserUserCrossRefDB(userId = MAIN_USER.id, friendId = savedUser.userId)
        )
    }

    fun getUserFriends(user: User): Flow<List<User>> =
        userLocalDataSource.getUserFriends(userId = user.id).map {
            it.map(UserDB::mapToUser)
        }

    fun getAllUsers(): Flow<List<User>> =
        userLocalDataSource.getAllUsers().map {
            it.map(UserDB::mapToUser)
        }
}

private fun User.mapToUserDBOnlyName(): UserDB =
    UserDB(name = name)

private fun User.mapToUserDB(): UserDB =
    UserDB(userId = id, name = name)

private fun UserDB.mapToUser(): User =
    User(id = userId, name = name)