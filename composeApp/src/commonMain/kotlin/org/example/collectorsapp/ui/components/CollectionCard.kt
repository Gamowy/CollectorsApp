package org.example.collectorsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.estimated_value
import kotlinproject.composeapp.generated.resources.image_collection_description
import kotlinproject.composeapp.generated.resources.not_available_description
import kotlinproject.composeapp.generated.resources.not_available_name
import kotlinproject.composeapp.generated.resources.placeholder
import org.example.collectorsapp.model.ItemsCollection
import org.example.collectorsapp.utils.byteArrayToImageBitmap
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CollectionCard(
    collection: ItemsCollection,
    collectionValue: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .weight(0.2f)
            .padding(8.dp, 8.dp, 8.dp, 2.dp),
            horizontalArrangement = Arrangement.Center,
            )
            {
            Text(
                text = collection.name.ifEmpty { stringResource(Res.string.not_available_name) },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Row (
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(8.dp, 2.dp, 8.dp, 8.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize().weight(0.6f)
                ) {
                if (collection.image != null) {
                    Image(
                        bitmap  = byteArrayToImageBitmap(collection.image!!),
                        contentDescription = stringResource(Res.string.image_collection_description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RoundedCornerShape(32.dp))
                    )
                } else {
                    Image(
                        painter = painterResource(Res.drawable.placeholder),
                        contentDescription = stringResource(Res.string.image_collection_description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RoundedCornerShape(32.dp))
                            .background(Color.White)
                        ,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = collection.category.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column (modifier = Modifier.fillMaxSize().weight(1f)) {
                Spacer(modifier = Modifier.height(8.dp))
                Box (modifier = Modifier.fillMaxSize()){
                    Text(
                        text = collection.description?.ifEmpty { stringResource(Res.string.not_available_description) }
                            ?: stringResource(Res.string.not_available_description),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${stringResource(Res.string.estimated_value)}${collectionValue}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                }
            }
        }
    }
}