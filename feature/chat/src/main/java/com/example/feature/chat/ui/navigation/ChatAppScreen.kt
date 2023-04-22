package com.example.feature.chat.ui.navigation

enum class ChatAppScreen(val title: String) {
    Home(title = "home"),
    Conversation(title = "conversation");

    companion object {
        fun valuesOf(title: String) = values().first {
            title.contains(it.title, ignoreCase = true)
        }
    }
}
