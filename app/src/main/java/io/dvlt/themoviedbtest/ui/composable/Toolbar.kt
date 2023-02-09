package io.dvlt.themoviedbtest.ui.composable

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun Toolbar(label: String) {
    TopAppBar(
        title = { Text(text = label, color = MaterialTheme.colorScheme.primary) },
        backgroundColor = MaterialTheme.colorScheme.background,
    )
}