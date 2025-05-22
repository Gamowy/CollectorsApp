package org.example.collectorsapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material3.ExtendedFloatingActionButton

@Composable
fun NewCollectionButton(){
    ExtendedFloatingActionButton(
        modifier = Modifier
            .height(60.dp)
            .width(150.dp),
        onClick = { /*TODO*/ },
        containerColor = Color(0xFFEADCF3),
        contentColor = Color.Black
    ) {
        Row (verticalAlignment = Alignment.CenterVertically){
            Icon(
                painter = painterResource(Res.drawable.search),
                contentDescription = "New Collection",
                tint = Color.Black
            )
            Text("New Collection", style = MaterialTheme.typography.labelSmall)
        }
    }
}