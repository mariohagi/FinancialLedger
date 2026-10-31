package com.example.catatankeuangan.ui.design

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Design System for FinancialLedger - Modern Fintech Style
 * 
 * Color palette inspired by Revolut/Monzo with professional teal primary
 * Light and Dark themes use same semantic color structure
 */

// Light Color Scheme
val LightColorScheme = ColorScheme(
    primary = Color(0xFF006D5B),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD1F5EB),
    onPrimaryContainer = Color(0xFF00211B),
    secondary = Color(0xFF4A6FA5),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD6E4F0),
    onSecondaryContainer = Color(0xFF07213D),
    tertiary = Color(0xFFE87C00),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFDDB3),
    onTertiaryContainer = Color(0xFF3A2700),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFAFCFC),
    onBackground = Color(0xFF1A1D1C),
    surface = Color(0xFFFEFEFE),
    onSurface = Color(0xFF1A1D1C),
    surfaceVariant = Color(0xFFF0F4F3),
    onSurfaceVariant = Color(0xFF424847),
    outline = Color(0xFF737978),
    outlineVariant = Color(0xFFC3C9C8),
    scrim = Color.Black,
    inverseSurface = Color(0xFF131716),
    inverseOnSurface = Color(0xFFE2E4E3),
    inversePrimary = Color(0xFF80E5D0),
    surfaceTint = Color(0xFF006D5B),
)

// Dark Color Scheme
val DarkColorScheme = ColorScheme(
    primary = Color(0xFF80E5D0),
    onPrimary = Color(0xFF003830),
    primaryContainer = Color(0xFF005345),
    onPrimaryContainer = Color(0xFFA7FFF1),
    secondary = Color(0xFFB8CCE4),
    onSecondary = Color(0xFF1D334B),
    secondaryContainer = Color(0xFF2D4D6E),
    onSecondaryContainer = Color(0xFFD6E4F0),
    tertiary = Color(0xFFFFB951),
    onTertiary = Color(0xFF332200),
    tertiaryContainer = Color(0xFF4E3300),
    onTertiaryContainer = Color(0xFFFFDDB3),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF131716),
    onBackground = Color(0xFFE2E4E3),
    surface = Color(0xFF131716),
    onSurface = Color(0xFFE2E4E3),
    surfaceVariant = Color(0xFF3F4544),
    onSurfaceVariant = Color(0xFFC3C9C8),
    outline = Color(0xFF8D9392),
    outlineVariant = Color(0xFF424847),
    scrim = Color.Black,
    inverseSurface = Color(0xFFFAFCFC),
    inverseOnSurface = Color(0xFF1A1D1C),
    inversePrimary = Color(0xFF006D5B),
    surfaceTint = Color(0xFF80E5D0),
)

// Semantic colors for Income/Expense
object FinanceColors {
    val IncomeGreen = Color(0xFF007B48)
    val IncomeGreenContainer = Color(0xFFBFF5DB)
    val IncomeGreenDark = Color(0xFF79DB9E)
    val IncomeGreenContainerDark = Color(0xFF005F33)

    val ExpenseRed = Color(0xFFC41A1A)
    val ExpenseRedContainer = Color(0xFFFDDADA)
    val ExpenseRedDark = Color(0xFFFFB4AB)
    val ExpenseRedContainerDark = Color(0xFF93000A)
}

// Spacing Scale (8dp base)
object FinanceSpacing {
    val XS = 4.dp
    val SM = 8.dp
    val MD = 16.dp
    val LG = 24.dp
    val XL = 32.dp
    val XXL = 48.dp
    val XXXL = 64.dp
}

// Shape System
object FinanceShapes {
    val ExtraSmall = androidx.compose.foundation.shape.RoundedCornerShape(4.dp)
    val Small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
    val Medium = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    val Large = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
    val ExtraLarge = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
    val Full = androidx.compose.foundation.shape.RoundedCornerShape(50.dp)
}

// Category Icons Mapping
object CategoryIcons {
    fun getIcon(categoryName: String): ImageVector = when (categoryName.lowercase()) {
        "food", "food & dining", "dining" -> Icons.Filled.Fastfood
        "transport", "transportation", "travel" -> Icons.Filled.DirectionsBus
        "shopping", "shop" -> Icons.Filled.ShoppingBag
        "entertainment", "fun", "games" -> Icons.Filled.VideogameAsset
        "salary", "income", "work" -> Icons.Filled.Work
        "freelance", "freelancer" -> Icons.Filled.LaptopMac
        "investment", "invest", "stocks" -> Icons.Filled.TrendingUp
        "bills", "utilities", "electricity", "water" -> Icons.Filled.Receipt
        "health", "medical", "healthcare" -> Icons.Filled.LocalHospital
        "education", "learning", "school" -> Icons.Filled.School
        "gift", "gifts" -> Icons.Filled.CardGiftcard
        "transfer", "transfers" -> Icons.Filled.SwapHoriz
        else -> Icons.Filled.Category
    }
}

// Indonesian Currency Formatter
object FinanceFormatter {
    fun formatIDR(amount: Double): String {
        val formatter = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("id", "ID"))
        return formatter.format(amount)
    }
}

// Date/Time Formatter
object FinanceDateFormatter {
    fun formatDate(date: java.util.Date): String {
        return java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale("id", "ID")).format(date)
    }

    fun formatTime(date: java.util.Date): String {
        return java.text.SimpleDateFormat("HH:mm", java.util.Locale("id", "ID")).format(date)
    }

    fun formatDateTime(date: java.util.Date): String {
        return java.text.SimpleDateFormat("dd MMM yyyy, HH:mm", java.util.Locale("id", "ID")).format(date)
    }
}

// Typography helpers - use MaterialTheme.typography directly in composables
// This object provides semantic names for common text styles
object FinanceTypography {
    // These are placeholder references - use MaterialTheme.typography.xxx in actual composables
    // Kept for semantic naming consistency
}
