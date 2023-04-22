package com.example.feature.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.chat.publ.model.User
import com.example.data.chat.publ.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            userRepository.insertUserComplete(user = User.MAIN_USER)
        }
    }

    val userFriends = userRepository.getUserFriends(User.MAIN_USER)

    fun insertNewFriend(friendName: String) = viewModelScope.launch {
        if (friendName.isEmpty()) return@launch
        userRepository.insertUserFriend(User(name = friendName))
    }
}
