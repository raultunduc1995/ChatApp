package com.example.data.chat.publ.model

data class Message(
    val sender: User,
    val receiver: User,
    val text: String,
)