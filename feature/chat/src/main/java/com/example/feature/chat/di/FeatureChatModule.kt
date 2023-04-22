package com.example.feature.chat.di

import com.example.feature.chat.viewmodel.ConversationViewModel
import com.example.feature.chat.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureChatModule = module {
    viewModelOf(::HomeViewModel)
    viewModel { params ->
        ConversationViewModel(
            friendUserId = params.get(),
            userRepository = get(),
            messageRepository = get()
        )
    }
}