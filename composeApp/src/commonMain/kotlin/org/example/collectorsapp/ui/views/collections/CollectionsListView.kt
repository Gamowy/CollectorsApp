package org.example.collectorsapp.ui.views.collections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.arrow_left
import kotlinproject.composeapp.generated.resources.sort_icon
import kotlinproject.composeapp.generated.resources.button_new_collection
import kotlinproject.composeapp.generated.resources.plus
import kotlinproject.composeapp.generated.resources.search_bar_hint
import kotlinproject.composeapp.generated.resources.top_bar_back_button_description
import org.example.collectorsapp.ui.components.CollectionCard
import org.example.collectorsapp.ui.components.DropdownMenu
import org.example.collectorsapp.ui.components.FabButton
import org.example.collectorsapp.ui.components.SearchBar
import org.example.collectorsapp.ui.components.SortButton
import org.example.collectorsapp.viewmodels.CollectionsListViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlinproject.composeapp.generated.resources.sort_category
import kotlinproject.composeapp.generated.resources.sort_condition

@Composable
fun CollectionsView(
    viewModel : CollectionsListViewModel,
    onCollectionClick: (Long) -> Unit,
    onAddClick: () -> Unit,
    currency: String,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var expanded by remember { mutableStateOf(false) }

    Box (modifier = modifier
        .fillMaxSize()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SearchBar(searchQuery = state.searchQuery,
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
        FabButton(
            stringResource(Res.string.button_new_collection),
            Res.drawable.plus,
            onClick = {
                onAddClick()
            },
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

