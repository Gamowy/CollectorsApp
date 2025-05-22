package org.example.collectorsapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GeminiView(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(4.dp)) {
        Text("Gemini")
    }
}