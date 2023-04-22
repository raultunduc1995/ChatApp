package com.example.data.chat.localdatasource

import com.example.core.databases.dao.UserDBDao
import com.example.core.databases.model.UserDB
import com.example.core.databases.model.UserUserCrossRefDB
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.withContext

class UserLocalDataSource(
    private val userDBDao: UserDBDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insert(user: UserDB) = withContext(ioDispatcher) {
        userDBDao.insert(user)
    }

    suspend fun insertUserFriend(vararg userUserCrossRefDBs: UserUserCrossRefDB) =
        withContext(ioDispatcher) {
            userDBDao.insert(*userUserCrossRefDBs)
        }

    fun getUserFriends(userId: Long): Flow<List<UserDB>> =
        userDBDao.getUserFriends(userId = userId).distinctUntilChanged()

    fun getAllUsers(): Flow<List<UserDB>> =
        userDBDao.getAll().distinctUntilChanged()

    suspend fun getUserById(userId: Long): UserDB =
        userDBDao.getUserById(userId)

    fun getUserByIdFlow(userId: Long): Flow<UserDB> =
        userDBDao.getUserByIdFlow(userId).distinctUntilChanged()
}
