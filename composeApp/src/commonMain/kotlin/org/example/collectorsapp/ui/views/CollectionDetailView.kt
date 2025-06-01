package org.example.collectorsapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.collectorsapp.ui.views.collections.CollectionsListViewModel

@Composable
fun CollectionDetailView(
    collectionId: Long,
    viewModel: CollectionsListViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.getCollectionById(collectionId)

    if (state != null) {
        Box(modifier = Modifier) {
            Text("Collection Detail View for ${state.collection.name}")
        }
    }
    else {
        onBack()
    }
}