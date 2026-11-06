package com.example.catatankeuangan

import android.content.Context
import androidx.compose.ui.graphics.Color

object ColorCustomizerPrefs {
    private const val PREF_BG_COLOR_LIGHT = "custom_bg_color_light"
    private const val PREF_BOX_COLOR_LIGHT = "custom_box_color_light"
    private const val PREF_PALETTE_LIGHT = "palette_id_light"

    private const val PREF_BG_COLOR_DARK = "custom_bg_color_dark"
    private const val PREF_BOX_COLOR_DARK = "custom_box_color_dark"
    private const val PREF_PALETTE_DARK = "palette_id_dark"

    fun saveBackgroundColor(context: Context, color: Color?, isDark: Boolean) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val key = if (isDark) PREF_BG_COLOR_DARK else PREF_BG_COLOR_LIGHT
        if (color == null) {
            prefs.edit().remove(key).apply()
        } else {
            val argb = (0xFF shl 24) or ((color.red * 255).toInt() shl 16) or ((color.green * 255).toInt() shl 8) or (color.blue * 255).toInt()
            val hex = String.format("#%06X", (0xFFFFFF and argb))
            prefs.edit().putString(key, hex).apply()
        }
    }

    fun loadBackgroundColor(context: Context, isDark: Boolean): Color? {
        val key = if (isDark) PREF_BG_COLOR_DARK else PREF_BG_COLOR_LIGHT
        val hex = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getString(key, null) ?: return null
        return try {
            Color(android.graphics.Color.parseColor(hex))
        } catch (e: Exception) {
            null
        }
    }

    fun saveBoxColor(context: Context, color: Color?, isDark: Boolean) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val key = if (isDark) PREF_BOX_COLOR_DARK else PREF_BOX_COLOR_LIGHT
        if (color == null) {
            prefs.edit().remove(key).apply()
        } else {
            val argb = (0xFF shl 24) or ((color.red * 255).toInt() shl 16) or ((color.green * 255).toInt() shl 8) or (color.blue * 255).toInt()
            val hex = String.format("#%06X", (0xFFFFFF and argb))
            prefs.edit().putString(key, hex).apply()
        }
    }

    fun loadBoxColor(context: Context, isDark: Boolean): Color? {
        val key = if (isDark) PREF_BOX_COLOR_DARK else PREF_BOX_COLOR_LIGHT
        val hex = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getString(key, null) ?: return null
        return try {
            Color(android.graphics.Color.parseColor(hex))
        } catch (e: Exception) {
            null
        }
    }

    fun savePaletteId(context: Context, paletteId: String, isDark: Boolean) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val key = if (isDark) PREF_PALETTE_DARK else PREF_PALETTE_LIGHT
        prefs.edit().putString(key, paletteId).apply()
    }

    fun loadPaletteId(context: Context, isDark: Boolean): String {
        val key = if (isDark) PREF_PALETTE_DARK else PREF_PALETTE_LIGHT
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getString(key, "teal_default") ?: "teal_default"
    }

    fun getContrastTextColor(backgroundColor: Color): Color {
        val luminance = 0.299f * backgroundColor.red + 0.587f * backgroundColor.green + 0.114f * backgroundColor.blue
        return if (luminance > 0.5f) Color(0xFF1A1A1A) else Color(0xFFFFFFFF)
    }
}
