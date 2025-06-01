package org.example.collectorsapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.add_collection
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FabButton(
    text: String,
    icon: DrawableResource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = { onClick() },
        contentColor = Color.Black
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Icon(
                painter = painterResource(icon),
                contentDescription = text,
                tint = Color.Black
            )
            Text(text, style = MaterialTheme.typography.labelMedium)
        }
    }
}