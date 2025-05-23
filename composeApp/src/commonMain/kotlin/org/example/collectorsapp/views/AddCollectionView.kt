package org.example.collectorsapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.button_new_collection
import kotlinproject.composeapp.generated.resources.placeholder
import org.example.collectorsapp.components.ClickableImage
import org.example.collectorsapp.components.SimpleDropdownMenu
import org.example.collectorsapp.model.CollectionCategory
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import kotlinproject.composeapp.generated.resources.collection_image_text
import kotlinproject.composeapp.generated.resources.collection_image_description
import kotlinproject.composeapp.generated.resources.collection_text_description
import kotlinproject.composeapp.generated.resources.collection_name_label_input
import kotlinproject.composeapp.generated.resources.collection_description_label_input
import kotlinproject.composeapp.generated.resources.input_placeholder
import kotlinproject.composeapp.generated.resources.collection_dropdown_label
import kotlinproject.composeapp.generated.resources.plus
import kotlinproject.composeapp.generated.resources.button_create_collection
import org.example.collectorsapp.components.NewCollectionButton

@Composable
fun AddCollectionView(modifier: Modifier = Modifier) {
    var selectedCategory by remember { mutableStateOf(CollectionCategory.entries[0].name) }

    Box(
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize()
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
                    .windowInsetsPadding(WindowInsets.safeDrawing)
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
                    .padding(4.dp)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                ClickableImage(
                    image = Res.drawable.placeholder,
                    contentDescription = stringResource(Res.string.collection_image_description),
                    onClick = { /*TODO*/ },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .width(150.dp)
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
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
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /*TODO*/ },
                    label = { Text(stringResource(Res.string.collection_name_label_input)) },
                    placeholder = { Text(stringResource(Res.string.input_placeholder)) },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(Color.White)
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /*TODO*/ },
                    label = { Text(stringResource(Res.string.collection_description_label_input)) },
                    placeholder = { Text(stringResource(Res.string.input_placeholder)) },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(4.dp)
                        .background(Color.White)
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                SimpleDropdownMenu(
                    label = stringResource(Res.string.collection_dropdown_label),
                    options = CollectionCategory.entries.map { it.name },
                    selectedOption = selectedCategory,
                    onOptionSelected = { selectedCategory= it },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(Color.White)
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                )
            }
        }
        NewCollectionButton(
            stringResource(Res.string.button_create_collection),
            Res.drawable.plus,
            onClick = { },
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}