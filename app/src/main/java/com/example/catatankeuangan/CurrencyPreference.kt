package com.example.catatankeuangan

import android.content.Context
import java.util.Locale

data class CurrencyInfo(
    val countryName: String,
    val currencyName: String,
    val symbol: String,
    val isoCode: String,
    val locale: Locale
)

object CurrencyPreference {
    val currencies = listOf(
        CurrencyInfo("Indonesia",      "Indonesian Rupiah", "Rp",  "IDR", Locale("id", "ID")),
        CurrencyInfo("United States",  "US Dollar",         "$",   "USD", Locale("en", "US")),
        CurrencyInfo("Japan",          "Japanese Yen",      "¥",   "JPY", Locale("ja", "JP")),
        CurrencyInfo("United Kingdom", "British Pound",     "£",   "GBP", Locale("en", "GB")),
        CurrencyInfo("Eurozone",       "Euro",              "€",   "EUR", Locale("de", "DE")),
        CurrencyInfo("South Korea",    "South Korean Won",  "₩",   "KRW", Locale("ko", "KR")),
        CurrencyInfo("Singapore",      "Singapore Dollar",  "S$",  "SGD", Locale("en", "SG")),
        CurrencyInfo("Malaysia",       "Malaysian Ringgit", "RM",  "MYR", Locale("ms", "MY")),
        CurrencyInfo("Australia",      "Australian Dollar", "A$",  "AUD", Locale("en", "AU")),
        CurrencyInfo("China",          "Chinese Yuan",      "¥",   "CNY", Locale("zh", "CN")),
        CurrencyInfo("India",          "Indian Rupee",      "₹",   "INR", Locale("hi", "IN")),
        CurrencyInfo("Thailand",       "Thai Baht",         "฿",   "THB", Locale("th", "TH")),
        CurrencyInfo("Philippines",    "Philippine Peso",   "₱",   "PHP", Locale("en", "PH")),
        CurrencyInfo("Vietnam",        "Vietnamese Đồng",   "₫",   "VND", Locale("vi", "VN")),
        CurrencyInfo("Brazil",         "Brazilian Real",    "R$",  "BRL", Locale("pt", "BR")),
    )

    private const val PREF_ISO = "currency_iso"
    private const val DEFAULT_ISO = "IDR"

    fun save(context: Context, info: CurrencyInfo) {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .edit().putString(PREF_ISO, info.isoCode).apply()
    }

    fun load(context: Context): CurrencyInfo {
        val iso = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .getString(PREF_ISO, DEFAULT_ISO) ?: DEFAULT_ISO
        return currencies.find { it.isoCode == iso } ?: currencies.first()
    }

    /** Format amount using the given currency's locale and symbol. */
    fun format(amount: Double, info: CurrencyInfo): String {
        return try {
            val formatter = java.text.NumberFormat.getCurrencyInstance(info.locale)
            formatter.currency = java.util.Currency.getInstance(info.isoCode)
            formatter.format(amount)
        } catch (e: Exception) {
            val nf = java.text.NumberFormat.getNumberInstance(info.locale)
            "${info.symbol}${nf.format(amount)}"
        }
    }
}
