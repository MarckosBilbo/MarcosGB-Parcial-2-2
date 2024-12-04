package com.example.marcosgb_parcial_2_2.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = AzulPrimario,
    onPrimary = TextoBlanco,
    secondary = AzulClaro,
    background = FondoClaro,
    surface = AzulClaro,
    onSurface = AzulOscuro
)

private val DarkColorScheme = darkColorScheme(
    primary = AzulClaro,
    onPrimary = AzulOscuro,
    secondary = AzulOscuro,
    background = FondoOscuro,
    surface = FondoOscuro,
    onSurface = TextoBlanco
)

@Composable
fun MarcosGBParcial2_2Theme(
    darkTheme: Boolean = false, // Cambiar a true para modo oscuro
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography, // Tipografías (opcional, definido abajo)
        content = content
    )
}