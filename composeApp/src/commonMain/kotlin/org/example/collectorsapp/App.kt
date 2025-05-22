package org.example.collectorsapp

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.collectorsapp.components.AppTitle
import org.example.collectorsapp.components.NavigationBar
import org.example.collectorsapp.components.NewCollectionButton
import org.example.collectorsapp.views.CollectionsView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            topBar = {
                TopAppBar(
                    title = {
                        AppTitle()
                    })
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.height(80.dp)
                ) {
                    NavigationBar()
                }
            },
            floatingActionButton = {
                NewCollectionButton()
            },
            floatingActionButtonPosition = androidx.compose.material3.FabPosition.End
        ) {
            CollectionsView(modifier = Modifier.padding(it))
        }
    }
}

