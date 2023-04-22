package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature.chat.ui.screens.ConversationScreen
import com.example.feature.chat.ui.screens.HomeScreen
import com.example.feature.chat.ui.theme.ChatAppTheme
import com.example.feature.chat.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

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
            if (currentBackstackEntry?.destination?.route?.equals("home") == true)
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
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        },
        content = { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(contentPadding)
            ) {
                composable("home") {
                    HomeScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
                composable(
                    "conversation/{userId}?userName={userName}",
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
                        viewModel = viewModel,
                        userId = backStackEntry.arguments?.getLong("userId") ?: 0L
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopAppBar(
    titleText: String,
    showBackArrow: Boolean,
    onNavigateBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = titleText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (showBackArrow) {
                BackArrow(onNavigateBackPressed = onNavigateBackPressed)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun BackArrow(onNavigateBackPressed: () -> Unit) {
    IconButton(onClick = onNavigateBackPressed) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}
