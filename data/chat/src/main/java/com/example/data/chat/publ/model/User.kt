package com.example.data.chat.publ.model

data class User(
    val id: Long = 0,
    val name: String
) {
    companion object {
        val MAIN_USER = User(id = 1, name = "Main User")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }


}