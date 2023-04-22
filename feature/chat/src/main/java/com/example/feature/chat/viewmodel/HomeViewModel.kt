package com.example.feature.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.chat.publ.model.User
import com.example.data.chat.publ.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            userRepository.insertUserComplete(user = UserRepository.MAIN_USER)
            userRepository.insertUserFriend(user = User(name = "Paul"))
            userRepository.insertUserFriend(user = User(name = "Andrei"))
        }
    }

    val userFriends = userRepository.getUserFriends(UserRepository.MAIN_USER)
}