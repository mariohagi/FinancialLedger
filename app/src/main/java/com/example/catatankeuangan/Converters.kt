package com.example.catatankeuangan

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromWalletSource(walletSource: WalletSource): String = walletSource.name

    @TypeConverter
    fun toWalletSource(value: String): WalletSource = WalletSource.valueOf(value)
}