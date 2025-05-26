package org.example.collectorsapp

import androidx.compose.ui.window.ComposeUIViewController
import org.example.collectorsapp.data.getCollectionDatabase
import org.example.collectorsapp.data.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {
    val repository = getCollectionDatabase(getDatabaseBuilder())
    App(repository)
}