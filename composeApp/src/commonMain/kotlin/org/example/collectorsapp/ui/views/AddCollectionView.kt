package org.example.collectorsapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.cancel_button
import kotlinproject.composeapp.generated.resources.placeholder
import org.example.collectorsapp.ui.components.ClickableImage
import org.example.collectorsapp.ui.components.SimpleDropdownMenu
import org.example.collectorsapp.model.CollectionCategory
import org.jetbrains.compose.resources.stringResource
import kotlinproject.composeapp.generated.resources.collection_image_text
import kotlinproject.composeapp.generated.resources.collection_image_description
import kotlinproject.composeapp.generated.resources.collection_text_description
import kotlinproject.composeapp.generated.resources.collection_name_label_input
import kotlinproject.composeapp.generated.resources.collection_description_label_input
import kotlinproject.composeapp.generated.resources.input_placeholder
import kotlinproject.composeapp.generated.resources.collection_dropdown_label
import kotlinproject.composeapp.generated.resources.save_button
import org.example.collectorsapp.model.ItemsCollection

@Composable
fun AddCollectionView(
    viewModel: CollectionsViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier) {

    var collectionName by remember { mutableStateOf("") }
    var collectionDescription by remember { mutableStateOf("") }
    var collectionImage by remember { mutableStateOf<ByteArray?>(null) }
    var collectionCategory by remember { mutableStateOf(CollectionCategory.Anything) }

    Box(
        modifier = modifier
            .padding(4.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    text = stringResource(Res.string.collection_image_text),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ClickableImage(
                    image = Res.drawable.placeholder,
                    contentDescription = stringResource(Res.string.collection_image_description),
                    onClick = { /*TODO*/ },
                    modifier = modifier
                        .height(150.dp)
                        .width(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray)
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    text = stringResource(Res.string.collection_text_description),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = collectionName,
                    onValueChange = {
                        collectionName = it
                    },
                    label = { Text(stringResource(Res.string.collection_name_label_input)) },
                    placeholder = { Text(stringResource(Res.string.input_placeholder)) },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = collectionDescription,
                    onValueChange = {
                        collectionDescription = it
                    },
                    label = { Text(stringResource(Res.string.collection_description_label_input)) },
                    placeholder = { Text(stringResource(Res.string.input_placeholder)) },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(4.dp)
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                SimpleDropdownMenu(
                    label = stringResource(Res.string.collection_dropdown_label),
                    options = CollectionCategory.entries.map { it.name },
                    selectedOption = collectionCategory.toString(),
                    onOptionSelected = { collectionCategory = stringToCollectionCategory(it) },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
            }
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp))
            {
                TextButton(modifier = Modifier.fillMaxWidth().weight(1f),
                    onClick = { navController.popBackStack() },
                ) {
                    Text(text = stringResource(Res.string.cancel_button), style = MaterialTheme.typography.titleMedium)
                }
                TextButton(modifier = Modifier.fillMaxWidth().weight(1f),
                    onClick = {
                        val collection = ItemsCollection(
                            name = collectionName,
                            description = collectionDescription,
                            image = collectionImage,
                            category = collectionCategory
                        )
                        viewModel.addCollection(collection)
                        navController.popBackStack()
                    },
                ) {
                    Text(text = stringResource(Res.string.save_button), style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

private fun stringToCollectionCategory(value: String): CollectionCategory {
    return try {
        CollectionCategory.valueOf(value)
    }
    catch  (e: IllegalArgumentException) {
        CollectionCategory.Anything
    }
}