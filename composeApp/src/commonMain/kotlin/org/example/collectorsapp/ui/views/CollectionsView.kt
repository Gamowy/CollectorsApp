package org.example.collectorsapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinproject.composeapp.generated.resources.Res
import org.example.collectorsapp.ui.components.CollectionCard
import org.example.collectorsapp.ui.components.NewCollectionButton
import org.example.collectorsapp.ui.components.SearchBar
import org.jetbrains.compose.resources.stringResource
import kotlinproject.composeapp.generated.resources.button_new_collection
import kotlinproject.composeapp.generated.resources.plus
import org.example.collectorsapp.NavigationDestination

@Composable
fun CollectionsView(
    viewModel : CollectionsViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val collectionList by viewModel.collectionsList.collectAsState()

    val listState = rememberLazyListState()
    Box (modifier = modifier
        .fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp, 0.dp),
        ) {
            item {
                SearchBar("", onValueChange = {
                    viewModel.searchCollections(it)
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(collectionList.size) { index ->
                CollectionCard(collection = collectionList[index])
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        NewCollectionButton(
            stringResource(Res.string.button_new_collection),
            Res.drawable.plus,
            onClick = {
                navController.navigate(NavigationDestination.AddCollectionView) },
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomEnd)
        )
    }
}

