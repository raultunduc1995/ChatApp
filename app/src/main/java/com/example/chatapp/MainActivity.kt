package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature.chat.ui.components.ChatTopAppBar
import com.example.feature.chat.ui.navigation.ChatAppScreen
import com.example.feature.chat.ui.screens.ConversationScreen
import com.example.feature.chat.ui.screens.HomeScreen
import com.example.feature.chat.ui.theme.ChatAppTheme
import com.example.feature.chat.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    val viewModel: HomeViewModel = koinViewModel()
    val navController = rememberNavController()
    val currentBackstackEntry by navController.currentBackStackEntryAsState()
    val titleText by remember(currentBackstackEntry) {
        derivedStateOf {
            val currentDestination = ChatAppScreen.valuesOf(
                title = currentBackstackEntry?.destination?.route ?: ChatAppScreen.Home.title
            )
            if (currentDestination == ChatAppScreen.Home)
                "Friends list"
            else
                currentBackstackEntry?.arguments?.getString("userName").orEmpty()
        }
    }

    Scaffold(
        topBar = {
            ChatTopAppBar(
                titleText = titleText,
                showBackArrow = currentBackstackEntry?.destination?.route?.equals("home") == false,
                onNavigateBackPressed = { navController.navigateUp() }
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = ChatAppScreen.Home.title,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(ChatAppScreen.Home.title) {
                HomeScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            composable(
                "${ChatAppScreen.Conversation.title}/{userId}?userName={userName}",
                arguments = listOf(
                    navArgument("userId") { type = NavType.LongType },
                    navArgument("userName") {
                        defaultValue = "User"
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                ConversationScreen(
                    navController = navController,
                    viewModel = koinViewModel {
                        parametersOf(backStackEntry.arguments?.getLong("userId") ?: 0L)
                    },
                )
            }
        }
    }
}
