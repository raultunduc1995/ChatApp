package com.example.data.chat.publ.utils

import com.example.core.databases.model.MessageDB
import com.example.core.databases.model.UserDB
import com.example.data.chat.publ.model.Message
import com.example.data.chat.publ.model.User

fun User.mapToUserDBOnlyName(): UserDB =
    UserDB(name = name)

fun User.mapToUserDB(): UserDB =
    UserDB(userId = id, name = name)

fun UserDB.mapToUser(): User =
    User(id = userId, name = name)

fun Message.mapToMessageDB(): MessageDB =
    MessageDB(
        senderId = sender.id,
        receiverId = receiver.id,
        text = text
    )

fun MessageDB.mapToMessage(firstUser: User, secondUser: User): Message =
    Message(
        sender = if (firstUser.id == senderId) firstUser else secondUser,
        receiver = if (firstUser.id == receiverId) firstUser else secondUser,
        text = text
    )
