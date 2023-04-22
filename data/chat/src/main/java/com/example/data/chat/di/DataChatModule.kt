package com.example.data.chat.di

import com.example.data.chat.localdatasource.MessagesLocalDataSource
import com.example.data.chat.localdatasource.UserLocalDataSource
import com.example.data.chat.publ.repository.MessageRepository
import com.example.data.chat.publ.repository.UserRepository
import org.koin.dsl.module

val dataChatModule = module {
    // Local data sources
    single {
        MessagesLocalDataSource(messageDBDao = get())
    }
    single {
        UserLocalDataSource(userDBDao = get())
    }
    // Repositories
    single {
        MessageRepository(messagesLocalDataSource = get())
    }
    single {
        UserRepository(userLocalDataSource = get())
    }
}