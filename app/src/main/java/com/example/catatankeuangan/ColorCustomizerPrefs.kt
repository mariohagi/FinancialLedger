package com.example.catatankeuangan

import android.content.Context
import androidx.compose.ui.graphics.Color

object ColorCustomizerPrefs {
    private const val PREF_BG_COLOR = "custom_bg_color"
    private const val PREF_BOX_COLOR = "custom_box_color"

    fun saveBackgroundColor(context: Context, color: Color?) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        if (color == null) {
            prefs.edit().remove(PREF_BG_COLOR).apply()
        } else {
            val argb = (0xFF shl 24) or ((color.red * 255).toInt() shl 16) or ((color.green * 255).toInt() shl 8) or (color.blue * 255).toInt()
            val hex = String.format("#%06X", (0xFFFFFF and argb))
            prefs.edit().putString(PREF_BG_COLOR, hex).apply()
        }
    }

    fun loadBackgroundColor(context: Context): Color? {
        val hex = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getString(PREF_BG_COLOR, null) ?: return null
        return try {
            Color(android.graphics.Color.parseColor(hex))
        } catch (e: Exception) {
            null
        }
    }

    fun saveBoxColor(context: Context, color: Color?) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        if (color == null) {
            prefs.edit().remove(PREF_BOX_COLOR).apply()
        } else {
            val argb = (0xFF shl 24) or ((color.red * 255).toInt() shl 16) or ((color.green * 255).toInt() shl 8) or (color.blue * 255).toInt()
            val hex = String.format("#%06X", (0xFFFFFF and argb))
            prefs.edit().putString(PREF_BOX_COLOR, hex).apply()
        }
    }

    fun loadBoxColor(context: Context): Color? {
        val hex = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getString(PREF_BOX_COLOR, null) ?: return null
        return try {
            Color(android.graphics.Color.parseColor(hex))
        } catch (e: Exception) {
            null
        }
    }

    fun getContrastTextColor(backgroundColor: Color): Color {
        // Luminance formula
        val luminance = 0.299f * backgroundColor.red + 0.587f * backgroundColor.green + 0.114f * backgroundColor.blue
        return if (luminance > 0.5f) Color(0xFF1A1A1A) else Color(0xFFFFFFFF)
    }
}
