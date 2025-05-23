package org.example.collectorsapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlinproject.composeapp.generated.resources.search_bar_hint
import kotlinproject.composeapp.generated.resources.search_bar_icon_description


@OptIn(ExperimentalMaterial3Api::class)
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
            .background(Color.White)
            .clip(RoundedCornerShape(32.dp)),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            Icon(
                painter = painterResource(Res.drawable.search),
                contentDescription = stringResource(Res.string.search_bar_icon_description),
                tint = Color.Gray
            )
        },
        maxLines = 1
    )
}
