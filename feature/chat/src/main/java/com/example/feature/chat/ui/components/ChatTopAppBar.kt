package com.example.feature.chat.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

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
