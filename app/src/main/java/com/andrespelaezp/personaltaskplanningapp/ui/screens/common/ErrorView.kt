package com.andrespelaezp.personaltaskplanningapp.ui.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorView() {
    // TODO: Implement ErrorView
    Box(
        contentAlignment = androidx.compose.ui.Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Error"
        )
    }

}