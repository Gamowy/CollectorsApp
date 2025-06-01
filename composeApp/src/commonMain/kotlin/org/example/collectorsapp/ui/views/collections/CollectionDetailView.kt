package org.example.collectorsapp.ui.views.collections

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.add_item
import kotlinproject.composeapp.generated.resources.category_label
import kotlinproject.composeapp.generated.resources.estimated_value
import kotlinproject.composeapp.generated.resources.image_collection_description
import kotlinproject.composeapp.generated.resources.items_in_collection
import kotlinproject.composeapp.generated.resources.not_available_description
import kotlinproject.composeapp.generated.resources.not_available_name
import kotlinproject.composeapp.generated.resources.placeholder
import kotlinproject.composeapp.generated.resources.plus
import org.example.collectorsapp.ui.components.FabButton
import org.example.collectorsapp.utils.byteArrayToImageBitmap
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CollectionDetailView(
    viewModel: CollectionDetailViewModel,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Card(modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
                elevation = CardDefaults.cardElevation(4.dp)) {
                Box {
                    if(state.collection.image != null) {
                        Image(
                            bitmap = byteArrayToImageBitmap(state.collection.image!!),
                            contentDescription = stringResource(Res.string.image_collection_description),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(Res.drawable.placeholder),
                            contentDescription = stringResource(Res.string.image_collection_description),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Canvas(modifier = modifier.fillMaxSize()) {
                        drawRect(
                            color = Color.Black,
                            alpha = 0.65f,
                            size = size
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.fillMaxSize().padding(12.dp, 8.dp)
                    ) {
                        Text(
                            text = state.collection.name.ifEmpty { stringResource(Res.string.not_available_name) },
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(0.2f)
                        )
                        Text(
                            text = state.collection.description?.ifEmpty { stringResource(Res.string.not_available_description) }
                                ?: stringResource(Res.string.not_available_description),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 8,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(0.6f)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.2f)
                        ) {
                            Text(text = "${stringResource(Res.string.category_label)}${state.collection.category.name}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(text = "${stringResource(Res.string.estimated_value)}${state.collectionValue.let {
                                if (it > 0) "$it" 
                                else "0"
                            }}",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(Res.string.items_in_collection),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        FabButton(
            text = stringResource(Res.string.add_item),
            icon = Res.drawable.plus,
            onClick = {
                onAddClick()
            },
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.BottomEnd)
        )
    }
}