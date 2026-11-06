package com.example.catatankeuangan

import android.content.Context
import androidx.compose.ui.graphics.Color

data class PaletteInfo(
    val id: String,
    val name: String,
    val primary: Color,
    val primaryContainer: Color,
    val onPrimary: Color = Color.White,
    val onPrimaryContainer: Color = Color(0xFF00211B)
)

object ColorPalette {
    val palettes = listOf(
        PaletteInfo("default", "Teal (Default)", Color(0xFF006D5B), Color(0xFFD1F5EB)),
        PaletteInfo("blue", "Blue", Color(0xFF1565C0), Color(0xFFD1E4FF)),
        PaletteInfo("purple", "Purple", Color(0xFF6750A4), Color(0xFFEADDFF)),
        PaletteInfo("teal", "Teal", Color(0xFF00796B), Color(0xFFB2DFDB)),
        PaletteInfo("green", "Green", Color(0xFF2E7D32), Color(0xFFC8E6C9)),
        PaletteInfo("orange", "Orange", Color(0xFFE65100), Color(0xFFFFCCBC)),
        PaletteInfo("red", "Red", Color(0xFFC62828), Color(0xFFFFCDD2)),
        PaletteInfo("pink", "Pink", Color(0xFFAD1457), Color(0xFFF8BBD0)),
        PaletteInfo("indigo", "Indigo", Color(0xFF283593), Color(0xFFC5CAE9)),
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