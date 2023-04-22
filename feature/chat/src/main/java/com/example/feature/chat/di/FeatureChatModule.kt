package com.example.feature.chat.di

import com.example.feature.chat.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureChatModule = module {
    viewModelOf(::HomeViewModel)
}