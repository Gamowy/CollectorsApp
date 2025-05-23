package org.example.collectorsapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.add_collection
import kotlinproject.composeapp.generated.resources.estimated_value
import kotlinproject.composeapp.generated.resources.image_collection_description
import kotlinproject.composeapp.generated.resources.not_available_description
import kotlinproject.composeapp.generated.resources.placeholder
import org.example.collectorsapp.model.Collection
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CollectionCard(collection: Collection) {
    val estimatedCollectionValue = collection.items.sumOf { it.estimatedValue ?: 0.0 }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ,
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (collection.imageBitmap != null) {
                    Image(
                        bitmap = collection.imageBitmap,
                        contentDescription = stringResource(Res.string.image_collection_description),
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Image(
                        painter = painterResource(Res.drawable.placeholder),
                        contentDescription = stringResource(Res.string.image_collection_description),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RoundedCornerShape(32.dp))
                            .shadow(elevation = 20.dp)
                            .background(Color.White)
                        ,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = collection.category.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column (modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = collection.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = collection.description ?: stringResource(Res.string.not_available_description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${stringResource(Res.string.estimated_value)}${estimatedCollectionValue}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}