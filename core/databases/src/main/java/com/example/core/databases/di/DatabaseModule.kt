package com.example.core.databases.di

import androidx.room.Room
import com.example.core.databases.AppDatabase
import com.example.core.databases.dao.MessageDBDao
import com.example.core.databases.dao.UserDBDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        val context = androidContext()
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "chat-database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single<MessageDBDao> {
        get<AppDatabase>().messageDBDao()
    }
    single<UserDBDao> {
        get<AppDatabase>().userDBDao()
    }
}
