package org.example.collectorsapp.ui.views.collections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.search_bar_hint
import kotlinproject.composeapp.generated.resources.sort_category
import org.example.collectorsapp.ui.components.CollectionCard
import org.example.collectorsapp.ui.components.SearchBar
import org.example.collectorsapp.ui.components.SortButton
import org.example.collectorsapp.viewmodels.CollectionsListViewModel
import org.jetbrains.compose.resources.stringResource

@Composable
fun CollectionsPickView(
    viewModel : CollectionsListViewModel,
    onCollectionClick: (Long) -> Unit,
    currency: String,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box (modifier = modifier
        .fillMaxSize()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SearchBar(
                        searchQuery = state.searchQuery,
                        searchHint = stringResource(Res.string.search_bar_hint),
                        onValueChange = { viewModel.searchCollections(it) },
                        modifier = modifier.weight(0.85f)
                    )
                    Box(modifier = modifier.weight(0.15f)){
                        SortButton(
                            modifier = Modifier
                                .size(35.dp),
                            textCategoryConditionASC = stringResource(Res.string.sort_category),
                            textCategoryConditionDESC = stringResource(Res.string.sort_category),
                            onNameAscClick = { viewModel.getAllCollectionsSortedByNameAsc() },
                            onNameDescClick = { viewModel.getAllCollectionsSortedByNameDesc() },
                            onCategoryConditionAscClick = { viewModel.getAllCollectionsSortedByCategoryAsc() },
                            onCategoryConditionDescClick = { viewModel.getAllCollectionsSortedByCategoryDesc() },
                            onValueAscClick = { viewModel.sortByValueAsc() },
                            onValueDescClick = { viewModel.sortByValueDesc() }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.searchResultsList.size) { index ->
                val collectionState = state.searchResultsList[index]
                CollectionCard(
                    collection = collectionState.collection,
                    collectionValue = collectionState.collectionValue,
                    onClick = { onCollectionClick(collectionState.collection.collectionId) },
                    currency = currency,
                    modifier = Modifier.animateItem(),
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}
