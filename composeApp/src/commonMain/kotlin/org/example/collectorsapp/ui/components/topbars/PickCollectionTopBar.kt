package org.example.collectorsapp.ui.components.topbars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.pick_collection_top_bar
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickCollectionTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.pick_collection_top_bar),
                style = MaterialTheme.typography.titleLarge,
            )
        },
        scrollBehavior = pinnedScrollBehavior(),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp)
    )
}