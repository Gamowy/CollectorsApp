package org.example.collectorsapp.ui.views.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.cancel_button
import kotlinproject.composeapp.generated.resources.input_placeholder
import kotlinproject.composeapp.generated.resources.item_description_label_input
import kotlinproject.composeapp.generated.resources.item_dropdown_label
import kotlinproject.composeapp.generated.resources.item_image_description
import kotlinproject.composeapp.generated.resources.item_image_subtext
import kotlinproject.composeapp.generated.resources.item_image_text
import kotlinproject.composeapp.generated.resources.item_name_label_input
import kotlinproject.composeapp.generated.resources.item_text_description
import kotlinproject.composeapp.generated.resources.item_value_label_input
import kotlinproject.composeapp.generated.resources.placeholder
import kotlinproject.composeapp.generated.resources.save_button
import org.example.collectorsapp.model.Condition
import org.example.collectorsapp.model.Item
import org.example.collectorsapp.ui.components.ClickableImage
import org.example.collectorsapp.ui.components.DropdownMenu
import org.example.collectorsapp.ui.views.collections.CollectionDetailViewModel
import org.example.collectorsapp.utils.processImage
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddEditItemView(
    collectionId: Long,
    itemId: Long? = null,
    viewModel: CollectionDetailViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var itemName by remember { mutableStateOf("") }
    var itemDescription by remember { mutableStateOf("") }
    var itemImage by remember { mutableStateOf<ByteArray?>(null) }
    var itemCondition by remember { mutableStateOf(Condition.New) }
    var itemEstimatedValue by remember { mutableStateOf<String?>("") }

    val editItemId by remember { mutableStateOf(itemId) }
    LaunchedEffect(editItemId) {
        if (editItemId != null) {
            val item = viewModel.getItemById(itemId!!)
            if (item != null) {
                itemName = item.name
                itemDescription = item.description ?: ""
                itemImage = item.imageBitmap
                itemCondition = item.condition
                itemEstimatedValue = item.estimatedValue.toString()
            }
            else {
                onBack()
            }
        }
    }

    val scope = rememberCoroutineScope()
    val imagePicker = rememberImagePickerLauncher(
        selectionMode = SelectionMode.Single,
        scope = scope,
        onResult = { byteArrays ->
            byteArrays.firstOrNull()?.let {
                itemImage = processImage(it)
            }
        }
    )

    Box(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = stringResource(Res.string.item_image_text),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    text = stringResource(Res.string.item_image_subtext),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                if (itemImage != null) {
                    Image(
                        bitmap = itemImage!!.toImageBitmap(),
                        contentDescription = stringResource(Res.string.item_image_description),
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .height(150.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                imagePicker.launch()
                            }
                    )
                } else {
                    ClickableImage(
                        image = Res.drawable.placeholder,
                        contentDescription = stringResource(Res.string.item_image_description),
                        onClick = {
                            imagePicker.launch()
                        },
                        modifier = modifier
                            .height(150.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.hsv(52f, 0.13f, 0.91f))
                    )
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(0.dp, 4.dp),
                    text = stringResource(Res.string.item_text_description),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = {
                        itemName = it
                    },
                    label = { Text(stringResource(Res.string.item_name_label_input)) },
                    placeholder = { Text(stringResource(Res.string.input_placeholder)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = modifier
                        .fillMaxWidth()
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = itemDescription,
                    onValueChange = {
                        itemDescription = it
                    },
                    label = { Text(stringResource(Res.string.item_description_label_input)) },
                    placeholder = { Text(stringResource(Res.string.input_placeholder)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = itemEstimatedValue ?: "",
                    onValueChange = {
                        itemEstimatedValue = it
                    },
                    label = { Text(stringResource(Res.string.item_value_label_input)) },
                    placeholder = { Text(stringResource(Res.string.input_placeholder)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
                    modifier = modifier
                        .fillMaxWidth()
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp)
            ) {
                DropdownMenu(
                    label = stringResource(Res.string.item_dropdown_label),
                    options = Condition.entries.map { it.name },
                    selectedOption = itemCondition.name,
                    onOptionSelected = { itemCondition = stringToItemCondition(it) },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(0.dp, 4.dp)
                )
            }
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth())
            {
                TextButton(modifier = Modifier.fillMaxWidth().weight(1f),
                    onClick = { onBack() },
                ) {
                    Text(text = stringResource(Res.string.cancel_button), style = MaterialTheme.typography.titleMedium)
                }
                TextButton(modifier = Modifier.fillMaxWidth().weight(1f),
                    enabled = itemName.isNotBlank(),
                    onClick = {
                        val estimatedValue = stringToDouble(itemEstimatedValue ?: "")
                        val item = Item(
                            itemId = editItemId ?: 0L,
                            collectionId = collectionId,
                            name = itemName,
                            description = itemDescription.ifBlank { null },
                            imageBitmap = itemImage,
                            estimatedValue = estimatedValue,
                            condition = itemCondition
                        )
                        viewModel.upsertItem(item)
                        onBack()
                    },
                ) {
                    Text(text = stringResource(Res.string.save_button), style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

private fun stringToItemCondition(value: String): Condition {
    return try {
        Condition.valueOf(value)
    }
    catch  (e: IllegalArgumentException) {
        Condition.New
    }
}

private fun stringToDouble(value: String): Double {
    return try {
        value.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }
}