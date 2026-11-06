package com.example.catatankeuangan

import android.content.Context
import androidx.compose.ui.graphics.Color

data class PaletteInfo(
    val id: String,
    val name: String,
    val primary: Color,
    val primaryContainer: Color,
    val secondary: Color,
    val secondaryContainer: Color,
    val onPrimary: Color = Color.White,
    val onPrimaryContainer: Color = Color(0xFF00211B)
)

object ColorPalette {
    val palettes = listOf(
        PaletteInfo(
            id = "default",
            name = "Teal (Default)",
            primary = Color(0xFF006D5B),
            primaryContainer = Color(0xFFD1F5EB),
            secondary = Color(0xFF4A6FA5),
            secondaryContainer = Color(0xFFD6E4F0)
        ),
        PaletteInfo(
            id = "blue",
            name = "Blue",
            primary = Color(0xFF1565C0),
            primaryContainer = Color(0xFFD1E4FF),
            secondary = Color(0xFF0288D1),
            secondaryContainer = Color(0xFFB3E5FC)
        ),
        PaletteInfo(
            id = "purple",
            name = "Purple",
            primary = Color(0xFF6750A4),
            primaryContainer = Color(0xFFEADDFF),
            secondary = Color(0xFF7D5260),
            secondaryContainer = Color(0xFFFFD8E4)
        ),
        PaletteInfo(
            id = "teal",
            name = "Teal",
            primary = Color(0xFF00796B),
            primaryContainer = Color(0xFFB2DFDB),
            secondary = Color(0xFF00897B),
            secondaryContainer = Color(0xFF80CBC4)
        ),
        PaletteInfo(
            id = "green",
            name = "Green",
            primary = Color(0xFF2E7D32),
            primaryContainer = Color(0xFFC8E6C9),
            secondary = Color(0xFF388E3C),
            secondaryContainer = Color(0xFFA5D6A7)
        ),
        PaletteInfo(
            id = "orange",
            name = "Orange",
            primary = Color(0xFFE65100),
            primaryContainer = Color(0xFFFFCCBC),
            secondary = Color(0xFFEF6C00),
            secondaryContainer = Color(0xFFFFE0B2)
        ),
        PaletteInfo(
            id = "red",
            name = "Red",
            primary = Color(0xFFC62828),
            primaryContainer = Color(0xFFFFCDD2),
            secondary = Color(0xFFD32F2F),
            secondaryContainer = Color(0xFFFFEBEE)
        ),
        PaletteInfo(
            id = "pink",
            name = "Pink",
            primary = Color(0xFFAD1457),
            primaryContainer = Color(0xFFF8BBD0),
            secondary = Color(0xFFC2185B),
            secondaryContainer = Color(0xFFFCE4EC)
        ),
        PaletteInfo(
            id = "indigo",
            name = "Indigo",
            primary = Color(0xFF283593),
            primaryContainer = Color(0xFFC5CAE9),
            secondary = Color(0xFF3F51B5),
            secondaryContainer = Color(0xFFE8EAF6)
        )
    )

    private const val PREF_PALETTE = "palette_id"
    private const val DEFAULT_PALETTE = "default"

    fun save(context: Context, palette: PaletteInfo) {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .edit().putString(PREF_PALETTE, palette.id).apply()
    }

    fun load(context: Context): PaletteInfo {
        val id = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getString(PREF_PALETTE, DEFAULT_PALETTE) ?: DEFAULT_PALETTE
        return palettes.find { it.id == id } ?: palettes.first()
    }

    fun fromId(id: String): PaletteInfo = palettes.find { it.id == id } ?: palettes.first()
}