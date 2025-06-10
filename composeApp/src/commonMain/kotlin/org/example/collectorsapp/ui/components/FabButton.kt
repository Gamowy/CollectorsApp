package org.example.collectorsapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun FabButton(
    text: String,
    icon: DrawableResource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = { onClick() },
        contentColor = Color.Black,
        containerColor = ButtonDefaults.buttonColors().containerColor
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