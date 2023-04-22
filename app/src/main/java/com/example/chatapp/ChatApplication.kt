package com.example.chatapp

import android.app.Application
import com.example.core.databases.di.databaseModule
import com.example.data.chat.di.dataChatModule
import com.example.feature.chat.di.featureChatModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ChatApplication)
            modules(
                databaseModule,
                dataChatModule,
                featureChatModule
            )
        }
    }
}
