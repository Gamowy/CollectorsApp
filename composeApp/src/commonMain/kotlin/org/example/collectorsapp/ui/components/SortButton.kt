package org.example.collectorsapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.sort_icon
import kotlinproject.composeapp.generated.resources.sort_ascending
import kotlinproject.composeapp.generated.resources.sort_descending
import kotlinproject.composeapp.generated.resources.sort_name
import kotlinproject.composeapp.generated.resources.sort_value
import kotlinproject.composeapp.generated.resources.button_sort
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SortButton(
    modifier: Modifier,
    textCategoryConditionASC : String,
    textCategoryConditionDESC : String,
    onNameAscClick : () -> Unit,
    onNameDescClick : () -> Unit,
    onCategoryConditionAscClick : () -> Unit,
    onCategoryConditionDescClick : () -> Unit,
    onValueAscClick : () -> Unit,
    onValueDescClick : () -> Unit,
){
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true },
        content = {
            Icon(
                painter = painterResource(Res.drawable.sort_icon),
                tint = Color.Gray,
                contentDescription = stringResource(Res.string.button_sort),
                modifier = modifier
            )
        }
    )
    androidx.compose.material3.DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text(stringResource(Res.string.sort_name)) },
            leadingIcon = { Icon(painterResource(Res.drawable.sort_ascending), contentDescription = null) },
            onClick = {
                onNameAscClick();
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text(stringResource(Res.string.sort_name)) },
            leadingIcon = { Icon(painterResource(Res.drawable.sort_descending), contentDescription = null) },
            onClick = {
                onNameDescClick();
                expanded = false
            }
        )
        DropdownMenuItem(
            text = { Text(textCategoryConditionASC) },
            leadingIcon = { Icon(painterResource(Res.drawable.sort_ascending), contentDescription = null) },
            onClick = {
                onCategoryConditionAscClick();
                expanded = false }
        )
        DropdownMenuItem(
            text = { Text(textCategoryConditionDESC) },
            leadingIcon = { Icon(painterResource(Res.drawable.sort_descending), contentDescription = null) },
            onClick = {
                onCategoryConditionDescClick();
                expanded = false }
        )
        DropdownMenuItem(
            text = { Text(stringResource(Res.string.sort_value)) },
            leadingIcon = { Icon(painterResource(Res.drawable.sort_ascending), contentDescription = null) },
            onClick = {
                onValueAscClick();
                expanded = false }
        )
        DropdownMenuItem(
            text = { Text(stringResource(Res.string.sort_value)) },
            leadingIcon = { Icon(painterResource(Res.drawable.sort_descending), contentDescription = null) },
            onClick = {
                onValueDescClick();
                expanded = false }
        )
    }
}