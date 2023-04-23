package com.example.data.chat.publ.model

sealed class MessageTemplate(
    open val sender: User,
    open val receiver: User
) {
    fun isMainUserTheSender(): Boolean =
        sender == User.MAIN_USER
}

data class LoadingMessage(
    override val sender: User,
    override val receiver: User
) : MessageTemplate(sender, receiver)

data class Message(
    override val sender: User,
    override val receiver: User,
    val text: String,
) : MessageTemplate(sender, receiver)