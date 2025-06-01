package org.example.collectorsapp.ui.views.collections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.button_new_collection
import kotlinproject.composeapp.generated.resources.plus
import org.example.collectorsapp.ui.components.CollectionCard
import org.example.collectorsapp.ui.components.NewCollectionButton
import org.example.collectorsapp.ui.components.SearchBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun CollectionsView(
    viewModel : CollectionsListViewModel,
    onCollectionClick: (Long) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box (modifier = modifier
        .fillMaxSize()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp, 0.dp),
        ) {
            item {
                SearchBar(state.searchQuery, onValueChange = {
                    viewModel.searchCollections(it)
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.searchResultsList.size) { index ->
                val collectionState = state.searchResultsList[index]
                CollectionCard(
                    collection = collectionState.collection,
                    collectionValue = collectionState.collectionValue,
                    onClick = { onCollectionClick(collectionState.collection.collectionId) },
                    modifier = Modifier.animateItem()
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        NewCollectionButton(
            stringResource(Res.string.button_new_collection),
            Res.drawable.plus,
            onClick = {
                onAddClick()
            },
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

