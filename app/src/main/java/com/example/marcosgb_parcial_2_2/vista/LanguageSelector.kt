package com.example.marcosgb_parcial_2_2.vista

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LanguageSelector(onLanguageSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val languages = listOf("es", "en")
    val languageNames = listOf("Español", "English")

    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        TextButton(onClick = { expanded = true }) {
            Text("Select Language")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEachIndexed { index, language ->
                DropdownMenuItem(
                    text = { Text(text = languageNames[index]) },
                    onClick = {
                        expanded = false
                        onLanguageSelected(language)
                    }
                )
            }
        }
    }
}