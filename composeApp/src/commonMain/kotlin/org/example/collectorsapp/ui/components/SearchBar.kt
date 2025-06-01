package org.example.collectorsapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.backspace
import kotlinproject.composeapp.generated.resources.search
import kotlinproject.composeapp.generated.resources.search_bar_clear_hint
import kotlinproject.composeapp.generated.resources.search_bar_hint
import kotlinproject.composeapp.generated.resources.search_bar_icon_description
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchBar(searchQuery: String, onValueChange: (String) -> Unit) {
    var searchText by remember { mutableStateOf(searchQuery) }
    TextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onValueChange(it)
        },
        placeholder = { Text(stringResource(Res.string.search_bar_hint)) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .shadow(2.dp, RoundedCornerShape(32.dp)),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.search),
                contentDescription = stringResource(Res.string.search_bar_icon_description),
                tint = Color.Gray
            )
        },
        trailingIcon = {
            AnimatedVisibility(searchText.isNotEmpty()) {
                Icon(
                    painter = painterResource(Res.drawable.backspace),
                    contentDescription = stringResource(Res.string.search_bar_clear_hint),
                    tint = Color.Gray,
                    modifier = Modifier
                        .clickable {
                            searchText = ""
                            onValueChange("")
                        }
                        .padding(16.dp)
                )
            }
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}
