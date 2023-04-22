package com.example.feature.chat.ui.components

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.chat.publ.model.User

@Composable
fun CircleUserAvatar(
    modifier: Modifier,
    avatarColor: Color,
    textStyle: androidx.compose.ui.text.TextStyle,
    textColor: Color,
    user: User
) {
    val avatarText by remember(user) {
        derivedStateOf {
            if (user == User.MAIN_USER)
                "Me"
            else
                user.name.first().uppercase()
        }
    }
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = avatarColor,
        shadowElevation = 4.dp
    ) {
        Text(
            text = avatarText,
            modifier = Modifier.wrapContentHeight(),
            color = textColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = textStyle
        )
    }
}
