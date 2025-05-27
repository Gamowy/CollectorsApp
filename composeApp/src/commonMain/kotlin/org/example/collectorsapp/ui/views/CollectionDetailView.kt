package org.example.collectorsapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun CollectionDetailView(
    collectionId: Long,
    navHost: NavHostController,
    viewModel: CollectionsViewModel,
    modifier: Modifier = Modifier
) {
    val collection = viewModel.getCollectionById(collectionId)

    if (collection != null) {
        Box(modifier = Modifier) {
            Text("Collection Detail View for ${collection.name}")
        }
    }
    else {
        navHost.popBackStack()
    }
}