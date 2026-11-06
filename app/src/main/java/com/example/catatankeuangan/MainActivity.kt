@file:OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)

package com.example.catatankeuangan

import android.os.Bundle
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.LaptopMac
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import android.graphics.Color as AndroidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.layout.PaddingValues
import android.content.res.Configuration
import com.example.catatankeuangan.ui.design.LightColorScheme
import com.example.catatankeuangan.ui.design.DarkColorScheme
import com.example.catatankeuangan.ui.design.FinanceShapes
import com.example.catatankeuangan.ui.design.FinanceColors
import com.example.catatankeuangan.ui.design.LocalCurrency
import com.example.catatankeuangan.ui.design.formatAmountDisplay
import com.example.catatankeuangan.CurrencyPreference
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.luminance
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

import com.example.catatankeuangan.ui.design.CategoryIcons
import com.example.catatankeuangan.ui.design.FinanceDateFormatter
import com.example.catatankeuangan.ui.design.FinanceFormatter

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.example.catatankeuangan.Category
import com.example.catatankeuangan.FinanceRepository
import com.example.catatankeuangan.Transaction
import com.example.catatankeuangan.Wallet
import com.example.catatankeuangan.WalletType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Typography
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.Modifier.Companion
import java.text.NumberFormat
import java.util.*
import androidx.compose.ui.graphics.vector.ImageVector
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat

// =====================================================================
// HOME SCREEN
// =====================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(repo: FinanceRepository) {
    val allTransactions by repo.allTransactions.collectAsState(initial = emptyList())
    val wallets by repo.allWallets.collectAsState(initial = emptyList())
    val income = allTransactions.filter { it.type == Transaction.Type.INCOME }.sumOf { it.amount }
    val expense = allTransactions.filter { it.type == Transaction.Type.EXPENSE }.sumOf { it.amount }
    val balance = income + expense

    val recentTransactions = allTransactions.reversed().take(5)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item { GreetingHeader() }
        item { BalanceCard(totalBalance = balance, wallets = wallets, allTransactions = allTransactions) }
        item { SummaryRow(income = income, expense = expense) }
        item { ChartCard(income = income, expense = expense) }
        item { RecentTransactionsSection(transactions = recentTransactions, onSeeAll = { }) }
    }
}

// Issue 3 FIX: Settings button removed from Home screen header
@Composable
fun GreetingHeader() {
    val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    val greeting = when {
        hour < 12 -> "Good Morning"
        hour < 17 -> "Good Afternoon"
        else -> "Good Evening"
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = greeting,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "Welcome back",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.W700
        )
    }
}

// =====================================================================
// BALANCE CARD - now uses dynamic Wallet entities instead of hardcoded WalletSource
// =====================================================================
@Composable
fun BalanceCard(
    totalBalance: Double,
    wallets: List<Wallet>,
    allTransactions: List<Transaction>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        shape = FinanceShapes.Large,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Total Balance",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                    Text(
                        FinanceFormatter.formatIDR(totalBalance),
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.W700,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f), RoundedCornerShape(28.dp))
                ) {
                    Icon(
                        Icons.Filled.TrendingUp,
                        contentDescription = "Trending",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            if (wallets.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text("Wallets", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W600, color = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    items(wallets) { wallet ->
                        val walletBalance = allTransactions
                            .filter { it.walletSource.equals(wallet.name, ignoreCase = true) || (it.walletSource == "WALLET" && wallet.name == "My Wallet") }
                            .sumOf { it.amount }
                        WalletSourceCard(
                            label = wallet.name,
                            amount = walletBalance,
                            icon = getWalletIcon(wallet.type),
                            iconColor = MaterialTheme.colorScheme.primary,
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            modifier = Modifier.width(160.dp)
                        )
                    }
                    item {
                        WalletSourceCard(
                            label = "Add Wallet",
                            amount = 0.0,
                            icon = Icons.Filled.Add,
                            iconColor = MaterialTheme.colorScheme.primary,
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            modifier = Modifier.width(160.dp)
                        )
                    }
                }
            }
        }
    }
}

fun getWalletIcon(type: WalletType): ImageVector {
    return when (type) {
        WalletType.CASH -> Icons.Filled.AccountBalanceWallet
        WalletType.BANK -> Icons.Filled.AccountBalance
        WalletType.CREDIT_CARD -> Icons.Filled.CreditCard
        WalletType.E_WALLET -> Icons.Filled.PhoneAndroid
        WalletType.INVESTMENT -> Icons.Filled.ShowChart
        WalletType.SAVINGS -> Icons.Filled.Savings
        WalletType.CRYPTO -> Icons.Filled.CurrencyBitcoin
        WalletType.OTHER -> Icons.Filled.AccountBalanceWallet
    }
}

@Composable
fun WalletSourceCard(
    label: String,
    amount: Double,
    icon: ImageVector,
    iconColor: Color,
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    // Derive text color from container luminance so it's readable on both light and dark containers
    val textColor = if (containerColor.luminance() > 0.3f)
        Color(0xFF003828) else MaterialTheme.colorScheme.onSurface
    val labelColor = if (containerColor.luminance() > 0.3f)
        Color(0xFF004D38) else MaterialTheme.colorScheme.onSurfaceVariant

    Card(
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = FinanceShapes.Medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(label, style = MaterialTheme.typography.labelMedium, color = labelColor,
                    maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                Box(modifier = Modifier.size(28.dp), contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = "", tint = iconColor, modifier = Modifier.size(18.dp))
                }
            }
            Text(
                FinanceFormatter.formatIDR(amount),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.W700,
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// =====================================================================
// SUMMARY & CHART
// =====================================================================
@Composable
fun SummaryRow(income: Double, expense: Double) {
    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SummaryCard(
            label = "Income",
            amount = income,
            icon = Icons.Filled.TrendingUp,
            iconColor = if (isDark) FinanceColors.IncomeGreenDark else FinanceColors.IncomeGreen,
            containerColor = if (isDark) FinanceColors.IncomeGreenContainerDark else FinanceColors.IncomeGreenContainer,
            textColor = if (isDark) FinanceColors.IncomeGreenDark else FinanceColors.IncomeGreen,
            modifier = Modifier.weight(1f)
        )
        SummaryCard(
            label = "Expense",
            amount = -expense,
            icon = Icons.Filled.TrendingDown,
            iconColor = if (isDark) FinanceColors.ExpenseRedDark else FinanceColors.ExpenseRed,
            containerColor = if (isDark) FinanceColors.ExpenseRedContainerDark else FinanceColors.ExpenseRedContainer,
            textColor = if (isDark) FinanceColors.ExpenseRedDark else FinanceColors.ExpenseRed,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SummaryCard(
    label: String,
    amount: Double,
    icon: ImageVector,
    iconColor: Color,
    containerColor: Color,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = FinanceShapes.Large,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(label, style = MaterialTheme.typography.labelLarge, color = textColor.copy(alpha = 0.75f))
                Box(
                    modifier = Modifier.size(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = "", tint = iconColor)
                }
            }
            Text(
                FinanceFormatter.formatIDR(amount),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.W700,
                color = textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ChartCard(income: Double, expense: Double) {
    val incomeVal = Math.max(0f, income.toFloat())
    val expenseVal = Math.max(0f, (-expense).toFloat())
    val hasData = incomeVal > 0 || expenseVal > 0

    Card(
        modifier = Modifier.fillMaxWidth().height(220.dp),
        shape = FinanceShapes.Large,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Spending Overview", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W600)
                if (hasData) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        LegendItem(color = FinanceColors.IncomeGreen, label = "Income")
                        LegendItem(color = FinanceColors.ExpenseRed, label = "Expense")
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                if (hasData) {
val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
                    val holeColor = if (isDark) {
                        AndroidColor.argb(
                            MaterialTheme.colorScheme.surface.alpha,
                            MaterialTheme.colorScheme.surface.red,
                            MaterialTheme.colorScheme.surface.green,
                            MaterialTheme.colorScheme.surface.blue
                        )
                    } else {
                        AndroidColor.WHITE
                    }
                    val centerTextColor = AndroidColor.argb(
                        MaterialTheme.colorScheme.onSurface.alpha,
                        MaterialTheme.colorScheme.onSurface.red,
                        MaterialTheme.colorScheme.onSurface.green,
                        MaterialTheme.colorScheme.onSurface.blue
                    )
                    AndroidView(
                        factory = { ctx ->
                            PieChart(ctx).apply {
                                legend.isEnabled = false
                                description.isEnabled = false
                                setUsePercentValues(true)
                                setDrawEntryLabels(false)
                                setCenterText("Income vs Expense")
                                setCenterTextSize(14f)
                                setCenterTextColor(centerTextColor)
                                setHoleRadius(50f)
                                setTransparentCircleRadius(55f)
                                setDrawCenterText(true)
                                setRotationAngle(270f)
                                setDragDecelerationFrictionCoef(0.95f)
                                setHoleColor(holeColor)
                            }
                        },
                        update = { chart ->
                            val entries = mutableListOf<PieEntry>()
                            if (incomeVal > 0) entries.add(PieEntry(incomeVal, "Income"))
                            if (expenseVal > 0) entries.add(PieEntry(expenseVal, "Expense"))
                            if (entries.isEmpty()) entries.add(PieEntry(1f, ""))
                            val dataSet = PieDataSet(entries, "").apply {
                                colors = listOf(
                                    AndroidColor.argb(
                                        FinanceColors.IncomeGreen.alpha,
                                        FinanceColors.IncomeGreen.red,
                                        FinanceColors.IncomeGreen.green,
                                        FinanceColors.IncomeGreen.blue
                                    ),
                                    AndroidColor.argb(
                                        FinanceColors.ExpenseRed.alpha,
                                        FinanceColors.ExpenseRed.red,
                                        FinanceColors.ExpenseRed.green,
                                        FinanceColors.ExpenseRed.blue
                                    )
                                )
                                valueTextSize = 12f
                                valueTextColor = AndroidColor.argb(
                                        Color.White.alpha,
                                        Color.White.red,
                                        Color.White.green,
                                        Color.White.blue
                                    )
                                sliceSpace = 2f
                                selectionShift = 5f
                            }
                            chart.data = PieData(dataSet)
                            chart.invalidate()
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                Icons.Filled.TrendingUp,
                                contentDescription = "No data",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                "No transactions yet",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                "Add your first transaction to see the chart",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LegendItem(color: Color, label: String) {
    Row(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier.size(10.dp)
                .background(color, RoundedCornerShape(5.dp))
        )
        Text(label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

// =====================================================================
// RECENT TRANSACTIONS
// =====================================================================
@Composable
fun RecentTransactionsSection(transactions: List<Transaction>, onSeeAll: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Recent Transactions", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W600)
            TextButton(onClick = onSeeAll) {
                Text("See All", style = MaterialTheme.typography.labelLarge)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        if (transactions.isEmpty()) {
            EmptyStateCard(
                icon = Icons.Filled.Receipt,
                title = "No transactions yet",
                subtitle = "Tap the + button to add your first transaction"
            )
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                transactions.forEach { tx ->
                    TransactionItem(tx = tx)
                }
            }
        }
    }
}

@Composable
fun EmptyStateCard(icon: ImageVector, title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        shape = FinanceShapes.Large,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(icon, contentDescription = "", tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f), modifier = Modifier.size(48.dp))
                Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun TransactionItem(tx: Transaction) {
    val isIncome = tx.type == Transaction.Type.INCOME
    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val amountColor = when {
        isIncome && isDark -> FinanceColors.IncomeGreenDark
        isIncome -> FinanceColors.IncomeGreen
        isDark -> FinanceColors.ExpenseRedDark
        else -> FinanceColors.ExpenseRed
    }
    val iconColor = amountColor
    val iconBgColor = when {
        isIncome && isDark -> FinanceColors.IncomeGreenContainerDark
        isIncome -> FinanceColors.IncomeGreenContainer
        isDark -> FinanceColors.ExpenseRedContainerDark
        else -> FinanceColors.ExpenseRedContainer
    }
    val amountPrefix = if (isIncome) "+" else "-"

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = FinanceShapes.Medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier.size(44.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(iconBgColor, RoundedCornerShape(22.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            CategoryIcons.getIcon(tx.category),
                            contentDescription = tx.category,
                            tint = iconColor,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
                Column {
                    Text(
                        tx.description,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            tx.category,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text("•", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outlineVariant)
                        Text(
                            FinanceDateFormatter.formatDateTime(tx.date),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text("•", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outlineVariant)
                        Text(
                            tx.walletSource,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Text(
                "$amountPrefix${FinanceFormatter.formatIDR(Math.abs(tx.amount))}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.W700,
                color = amountColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.widthIn(max = 120.dp)
            )
        }
    }
}

// =====================================================================
// ANALYTICS SCREEN — Pie chart, daily/monthly/yearly, income/expense switch
// =====================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(repo: FinanceRepository, navController: NavHostController) {
    val allTransactions by repo.allTransactions.collectAsState(initial = emptyList())
    
    var periodFilter by remember { mutableStateOf("Monthly") } // Daily, Monthly, Yearly
    var txType by remember { mutableStateOf(Transaction.Type.EXPENSE) } // INCOME, EXPENSE

    val now = remember { java.util.Calendar.getInstance() }
    var selectedYear by remember { mutableStateOf(now.get(java.util.Calendar.YEAR)) }
    var selectedMonth by remember { mutableStateOf(now.get(java.util.Calendar.MONTH) + 1) }
    var selectedDay by remember { mutableStateOf(now.get(java.util.Calendar.DAY_OF_MONTH)) }

    // Filter transactions based on period & type
    val filteredTransactions = remember(allTransactions, periodFilter, selectedYear, selectedMonth, selectedDay, txType) {
        allTransactions.filter { tx ->
            if (tx.type != txType) return@filter false
            val cal = java.util.Calendar.getInstance().apply { time = tx.date }
            val y = cal.get(java.util.Calendar.YEAR)
            val m = cal.get(java.util.Calendar.MONTH) + 1
            val d = cal.get(java.util.Calendar.DAY_OF_MONTH)
            when (periodFilter) {
                "Daily" -> y == selectedYear && m == selectedMonth && d == selectedDay
                "Monthly" -> y == selectedYear && m == selectedMonth
                "Yearly" -> y == selectedYear
                else -> true
            }
        }
    }

    val totalAmount = filteredTransactions.sumOf { Math.abs(it.amount) }

    // Group by category
    val categoryMap = remember(filteredTransactions) {
        val map = mutableMapOf<String, Double>()
        filteredTransactions.forEach { tx ->
            val cat = if (tx.category.isBlank()) "General" else tx.category
            map[cat] = (map[cat] ?: 0.0) + Math.abs(tx.amount)
        }
        map.entries.sortedByDescending { it.value }
    }

    val chartColors = listOf(
        AndroidColor.parseColor("#1565C0"),
        AndroidColor.parseColor("#00796B"),
        AndroidColor.parseColor("#E65100"),
        AndroidColor.parseColor("#6750A4"),
        AndroidColor.parseColor("#2E7D32"),
        AndroidColor.parseColor("#C62828"),
        AndroidColor.parseColor("#AD1457"),
        AndroidColor.parseColor("#0288D1")
    )

    val composeChartColors = listOf(
        Color(0xFF1565C0), Color(0xFF00796B), Color(0xFFE65100), Color(0xFF6750A4),
        Color(0xFF2E7D32), Color(0xFFC62828), Color(0xFFAD1457), Color(0xFF0288D1)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analytics", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.W600) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Period Filter Chips
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Daily", "Monthly", "Yearly").forEach { filter ->
                        FilterChip(
                            selected = periodFilter == filter,
                            onClick = { periodFilter = filter },
                            label = { Text(filter) },
                            modifier = Modifier.weight(1f),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }

            // Income / Expense Switch
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { txType = Transaction.Type.EXPENSE },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (txType == Transaction.Type.EXPENSE) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (txType == Transaction.Type.EXPENSE) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Text("Expenses")
                    }
                    Button(
                        onClick = { txType = Transaction.Type.INCOME },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (txType == Transaction.Type.INCOME) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (txType == Transaction.Type.INCOME) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Text("Income")
                    }
                }
            }

            // Date selector display / buttons
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = FinanceShapes.Medium,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = when (periodFilter) {
                                                            "Daily" -> "$selectedDay/$selectedMonth/$selectedYear"
                                                            "Monthly" -> "Month: $selectedMonth, Year: $selectedYear"
                                                            else -> "Year: $selectedYear"
                                                        },
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.W600
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            TextButton(onClick = {
                                if (periodFilter == "Daily") selectedDay = (selectedDay - 1).coerceAtLeast(1)
                                else if (periodFilter == "Monthly") selectedMonth = if (selectedMonth > 1) selectedMonth - 1 else { selectedYear--; 12 }
                                else selectedYear--
                            }) { Text("<") }
                            TextButton(onClick = {
                                if (periodFilter == "Daily") selectedDay++
                                else if (periodFilter == "Monthly") selectedMonth = if (selectedMonth < 12) selectedMonth + 1 else { selectedYear++; 1 }
                                else selectedYear++
                            }) { Text(">") }
                        }
                    }
                }
            }

            // Pie Chart Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    shape = FinanceShapes.Large,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "${txType.name} Breakdown",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.W600
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            if (filteredTransactions.isEmpty()) {
                                Text(
                                    "No transactions for this period",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            } else {
                                val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
                                val holeColor = if (isDark) {
                                    AndroidColor.argb(
                                        MaterialTheme.colorScheme.surface.alpha,
                                        MaterialTheme.colorScheme.surface.red,
                                        MaterialTheme.colorScheme.surface.green,
                                        MaterialTheme.colorScheme.surface.blue
                                    )
                                } else {
                                    AndroidColor.WHITE
                                }
                                val centerTextColor = AndroidColor.argb(
                                    MaterialTheme.colorScheme.onSurface.alpha,
                                    MaterialTheme.colorScheme.onSurface.red,
                                    MaterialTheme.colorScheme.onSurface.green,
                                    MaterialTheme.colorScheme.onSurface.blue
                                )
                                AndroidView(
                                    factory = { ctx ->
                                        PieChart(ctx).apply {
                                            legend.isEnabled = false
                                            description.isEnabled = false
                                            setUsePercentValues(true)
                                            setDrawEntryLabels(false)
                                            setCenterText(FinanceFormatter.formatIDR(totalAmount))
                                            setCenterTextSize(12f)
                                            setCenterTextColor(centerTextColor)
                                            setHoleRadius(50f)
                                            setTransparentCircleRadius(55f)
                                            setDrawCenterText(true)
                                            setHoleColor(holeColor)
                                        }
                                    },
                                    update = { chart ->
                                        val entries = categoryMap.mapIndexed { index, entry ->
                                            PieEntry(entry.value.toFloat(), entry.key)
                                        }
                                        val dataSet = PieDataSet(entries, "").apply {
                                            colors = chartColors.take(entries.size)
                                            sliceSpace = 2f
                                        }
                                        chart.data = PieData(dataSet).apply {
                                            setValueTextSize(10f)
                                            setValueTextColor(AndroidColor.WHITE)
                                        }
                                        chart.centerText = FinanceFormatter.formatIDR(totalAmount)
                                        chart.invalidate()
                                    },
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }

            // Category Breakdown List Header
            item {
                Text(
                    text = "Category Breakdown",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W600
                )
            }

            if (categoryMap.isEmpty()) {
                item {
                    Text(
                        "No data available",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            } else {
                items(categoryMap.size) { index ->
                    val (categoryName, amount) = categoryMap[index]
                    val percentage = if (totalAmount > 0) (amount / totalAmount * 100) else 0.0
                    val color = composeChartColors[index % composeChartColors.size]

                    Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    navController.navigate("category_details/$categoryName/${txType.name}/$periodFilter/$selectedYear/$selectedMonth/$selectedDay")
                                                },
                                            shape = FinanceShapes.Medium,
                                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Row(
                                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.weight(1f)
                                                ) {
                                                    Box(
                                                        modifier = Modifier
                                                            .size(16.dp)
                                                            .clip(CircleShape)
                                                            .background(color)
                                                    )
                                Column {
                                    Text(
                                        text = categoryName,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.W500
                                    )
                                    Text(
                                        text = String.format("%.1f%%", percentage),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Text(
                                text = FinanceFormatter.formatIDR(amount),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.W600,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailsScreen(
    repo: FinanceRepository,
    navController: NavHostController,
    categoryName: String,
    txType: String,
    periodFilter: String,
    year: Int,
    month: Int,
    day: Int
) {
    val allTransactions by repo.allTransactions.collectAsState(initial = emptyList())
    val txTypeObj = if (txType == "INCOME") Transaction.Type.INCOME else Transaction.Type.EXPENSE
    val filteredTransactions = remember(allTransactions, categoryName, txTypeObj, periodFilter, year, month, day) {
        allTransactions.filter { tx ->
            if (tx.category != categoryName || tx.type != txTypeObj) return@filter false
            val cal = java.util.Calendar.getInstance().apply { time = tx.date }
            val y = cal.get(java.util.Calendar.YEAR)
            val m = cal.get(java.util.Calendar.MONTH) + 1
            val d = cal.get(java.util.Calendar.DAY_OF_MONTH)
            when (periodFilter) {
                "Daily" -> y == year && m == month && d == day
                "Monthly" -> y == year && m == month
                "Yearly" -> y == year
                else -> true
            }
        }.sortedByDescending { it.date }
    }
    val totalAmount = filteredTransactions.sumOf { Math.abs(it.amount) }
    val txCount = filteredTransactions.size

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.W600) },
                navigationIcon = {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                                    }
                                },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.background,
                                    titleContentColor = MaterialTheme.colorScheme.onSurface
                                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = FinanceShapes.Medium,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Total: ${FinanceFormatter.formatIDR(totalAmount)}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.W600)
                        Text("Count: $txCount", style = MaterialTheme.typography.bodyMedium)
                        Text("Type: $txType", style = MaterialTheme.typography.bodyMedium)
                        Text("Period: $periodFilter", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            item {
                val walletTotals = remember(filteredTransactions) {
                    val map = mutableMapOf<String, Float>()
                    filteredTransactions.forEach { tx ->
                        val key = tx.walletSource.ifBlank { "Unknown" }
                        map[key] = (map[key] ?: 0f) + Math.abs(tx.amount).toFloat()
                    }
                    map
                }
                if (walletTotals.isNotEmpty()) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = FinanceShapes.Medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Wallet Distribution", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W600)
                            AndroidView(
                                factory = { ctx ->
                                    PieChart(ctx).apply {
                                        legend.isEnabled = true
                                        description.isEnabled = false
                                        setUsePercentValues(true)
                                        setDrawEntryLabels(false)
                                        setCenterText("By Wallet")
                                        setCenterTextSize(12f)
                                        setHoleRadius(50f)
                                        setTransparentCircleRadius(55f)
                                        setDrawCenterText(true)
                                    }
                                },
                                update = { chart ->
                                    val entries = walletTotals.map { PieEntry(it.value, it.key) }
                                    val dataSet = PieDataSet(entries, "").apply {
                                        colors = listOf(
                                            AndroidColor.parseColor("#4CAF50"),
                                            AndroidColor.parseColor("#2196F3"),
                                            AndroidColor.parseColor("#FF9800"),
                                            AndroidColor.parseColor("#9C27B0"),
                                            AndroidColor.parseColor("#F44336")
                                        )
                                    }
                                    chart.data = PieData(dataSet)
                                    chart.invalidate()
                                },
                                modifier = Modifier.fillMaxWidth().height(200.dp)
                            )
                        }
                    }
                }
            }
                    if (filteredTransactions.isEmpty()) {
                        item {
                            Text("No transactions", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(vertical = 16.dp))
                        }
                    } else {
                items(filteredTransactions.size) { index ->
                    val tx = filteredTransactions[index]
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = FinanceShapes.Medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(tx.description.ifBlank { "Transaction" }, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.W500)
                                Text(tx.date.toString(), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text("Wallet: ${tx.walletSource}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Text(
                                FinanceFormatter.formatIDR(Math.abs(tx.amount)),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.W600,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(repo: FinanceRepository, navController: NavHostController) {
    val allTransactions by repo.allTransactions.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    val now = remember { java.util.Calendar.getInstance() }
    val currentYear  = now.get(java.util.Calendar.YEAR)
    val currentMonth = now.get(java.util.Calendar.MONTH) + 1 // 1-based

    // Derive months from transactions, always include current month
    val availableMonths = remember(allTransactions) {
        val months = mutableSetOf(Pair(currentYear, currentMonth))
        allTransactions.forEach { tx ->
            val cal = java.util.Calendar.getInstance().apply { time = tx.date }
            months.add(Pair(cal.get(java.util.Calendar.YEAR), cal.get(java.util.Calendar.MONTH) + 1))
        }
        months.sortedWith(compareBy({ it.first }, { it.second }))
    }

    var selectedYear  by remember { mutableStateOf(currentYear) }
    var selectedMonth by remember { mutableStateOf(currentMonth) }

    val monthlyTransactions by repo.getTransactionsByYearMonth(selectedYear, selectedMonth)
        .collectAsState(initial = emptyList())

    val monthIncome  = monthlyTransactions.filter { it.type == Transaction.Type.INCOME }.sumOf { it.amount }
    val monthExpense = monthlyTransactions.filter { it.type == Transaction.Type.EXPENSE }.sumOf { it.amount }

    val monthListState = rememberLazyListState()

    // Scroll to selected month chip on first load
    LaunchedEffect(availableMonths, selectedYear, selectedMonth) {
        val idx = availableMonths.indexOfFirst { it.first == selectedYear && it.second == selectedMonth }
        if (idx >= 0) monthListState.animateScrollToItem(idx)
    }

    Column(modifier = Modifier.fillMaxSize()) {
         TopAppBar(
            title = { Text("Transactions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.W600) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            )
        )

        // Month selector bar
        LazyRow(
            state = monthListState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(availableMonths) { (year, month) ->
                val isSelected = year == selectedYear && month == selectedMonth
                Surface(
                    onClick = { selectedYear = year; selectedMonth = month },
                    shape = FinanceShapes.Full,
                    color = if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.height(36.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            FinanceDateFormatter.formatMonthYear(year, month - 1),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSelected) FontWeight.W700 else FontWeight.W400,
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                                    else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Monthly summary row
        if (monthlyTransactions.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val currency = LocalCurrency.current
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = FinanceShapes.Medium,
                    color = FinanceColors.IncomeGreenContainer
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Income", style = MaterialTheme.typography.labelSmall, color = FinanceColors.IncomeGreen)
                        Text(
                            FinanceFormatter.format(monthIncome, currency),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.W700,
                            color = FinanceColors.IncomeGreen
                        )
                    }
                }
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = FinanceShapes.Medium,
                    color = FinanceColors.ExpenseRedContainer
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Expense", style = MaterialTheme.typography.labelSmall, color = FinanceColors.ExpenseRed)
                        Text(
                            FinanceFormatter.format(-monthExpense, currency),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.W700,
                            color = FinanceColors.ExpenseRed
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        Box(modifier = Modifier.fillMaxSize()) {
            if (monthlyTransactions.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            Icons.Filled.Receipt,
                            contentDescription = "No transactions",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
                            modifier = Modifier.size(64.dp)
                        )
                        Text(
                            "No transactions this month",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            "Tap + to add a transaction",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 80.dp)
                ) {
                    items(monthlyTransactions) { tx ->
                        TransactionItemWithActions(
                            tx = tx,
                            onEdit = { navController.navigate("add_transaction/${tx.id}") },
                            onDelete = { scope.launch(Dispatchers.IO) { repo.delete(tx) } }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionItemWithActions(
    tx: Transaction,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val isIncome = tx.type == Transaction.Type.INCOME
    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val amountColor = when {
        isIncome && isDark -> FinanceColors.IncomeGreenDark
        isIncome -> FinanceColors.IncomeGreen
        isDark -> FinanceColors.ExpenseRedDark
        else -> FinanceColors.ExpenseRed
    }
    val iconColor = amountColor
    val iconBgColor = when {
        isIncome && isDark -> FinanceColors.IncomeGreenContainerDark
        isIncome -> FinanceColors.IncomeGreenContainer
        isDark -> FinanceColors.ExpenseRedContainerDark
        else -> FinanceColors.ExpenseRedContainer
    }
    val amountPrefix = if (isIncome) "+" else "-"

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = FinanceShapes.Medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier.size(44.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(iconBgColor, RoundedCornerShape(22.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            CategoryIcons.getIcon(tx.category),
                            contentDescription = tx.category,
                            tint = iconColor,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        tx.description,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(tx.category, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("•", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outlineVariant)
                        Text(FinanceDateFormatter.formatDateTime(tx.date), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    "$amountPrefix${FinanceFormatter.formatIDR(Math.abs(tx.amount))}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.W700,
                    color = amountColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.widthIn(max = 120.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onEdit, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(18.dp))
                    }
                    IconButton(onClick = onDelete, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}

// =====================================================================
// ADD / EDIT TRANSACTION SCREEN
// Issues 4, 5, 6 FIXED here
// =====================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    repo: FinanceRepository,
    navController: NavHostController,
    txId: Int
) {
    val context = LocalContext.current
    val categories by repo.allCategories.collectAsState(initial = emptyList())
    val wallets by repo.allWallets.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    val isEditing = txId != -1
    val allTransactions by repo.allTransactions.collectAsState(initial = emptyList())

    // Issue 6 FIX: Use LaunchedEffect to properly load the existing transaction
    var description by remember { mutableStateOf("") }
    var amountStr by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(Transaction.Type.INCOME) }
    var category by remember { mutableStateOf("General") }
    var walletSource by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(Date()) }
    var isInitialized by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }

    // Task 7: amount display with thousands separator; amountStr holds raw digits
    var amountDisplay by remember { mutableStateOf("") }

    // Task 5: description autocomplete
    var descSuggestions by remember { mutableStateOf(emptyList<String>()) }
    var showSuggestions by remember { mutableStateOf(false) }

    // Load existing transaction data when editing
    LaunchedEffect(allTransactions, isEditing, txId, wallets, categories) {
        if (isEditing && !isInitialized && allTransactions.isNotEmpty()) {
            val tx = allTransactions.find { it.id == txId }
            if (tx != null) {
                description = tx.description
                amountStr = Math.abs(tx.amount).toLong().toString()
                val (disp, _) = formatAmountDisplay(amountStr)
                amountDisplay = disp
                type = tx.type
                category = tx.category
                walletSource = tx.walletSource
                selectedDate = tx.date
                isInitialized = true
            }
        } else if (!isEditing && !isInitialized) {
            // Set default category
            if (categories.isNotEmpty()) {
                category = categories.first().name
            }
            if (wallets.isNotEmpty()) {
                walletSource = wallets.first().name
            }
            if (categories.isNotEmpty() && wallets.isNotEmpty()) {
                isInitialized = true
            }
        } else if (walletSource.isBlank() && wallets.isNotEmpty()) {
            walletSource = wallets.first().name
        }
    }

    val dateFormatter = remember { java.text.SimpleDateFormat("dd MMM yyyy", Locale("id", "ID")) }
    val timeFormatter = remember { java.text.SimpleDateFormat("HH:mm", Locale("id", "ID")) }

    // Issue 5 FIX: Use proper lambda that shows the dialog
    val showDatePicker = {
        val calendar = java.util.Calendar.getInstance()
        calendar.time = selectedDate
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val cal = java.util.Calendar.getInstance()
                cal.time = selectedDate
                cal.set(year, month, dayOfMonth)
                selectedDate = cal.time
            },
            calendar.get(java.util.Calendar.YEAR),
            calendar.get(java.util.Calendar.MONTH),
            calendar.get(java.util.Calendar.DAY_OF_MONTH)
        ).show()
    }

    val showTimePicker = {
        val calendar = java.util.Calendar.getInstance()
        calendar.time = selectedDate
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                val cal = java.util.Calendar.getInstance()
                cal.time = selectedDate
                cal.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(java.util.Calendar.MINUTE, minute)
                selectedDate = cal.time
            },
            calendar.get(java.util.Calendar.HOUR_OF_DAY),
            calendar.get(java.util.Calendar.MINUTE),
            true
        ).show()
    }

    val canSave = description.trim().isNotEmpty() && amountStr.toDoubleOrNull() != null && (amountStr.toDoubleOrNull() ?: 0.0) > 0

    Column(modifier = Modifier.fillMaxSize()) {
        // Top App Bar
        TopAppBar(
            title = { Text(if (isEditing) "Edit Transaction" else "Add Transaction", fontWeight = FontWeight.W600) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            )
        )

        // Scrollable Form Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TypeSelector(
                selectedType = type,
                onTypeChange = { newType ->
                    type = newType
                }
            )

            OutlinedTextField(
                value = description,
                onValueChange = { v ->
                    description = v
                    showSuggestions = v.isNotBlank()
                    if (v.isNotBlank()) {
                        scope.launch {
                            descSuggestions = repo.getDescriptionSuggestions(v)
                        }
                    } else {
                        descSuggestions = emptyList()
                    }
                },
                label = { Text("Description") },
                placeholder = { Text("e.g., Lunch, Salary, Shopping") },
                leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = "") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = FinanceShapes.Medium
            )
            // Task 5: Autocomplete suggestions
            if (showSuggestions && descSuggestions.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = FinanceShapes.Medium,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column {
                        descSuggestions.forEach { suggestion ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        description = suggestion
                                        showSuggestions = false
                                        descSuggestions = emptyList()
                                    }
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(
                                    Icons.Filled.Receipt,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    suggestion,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            if (suggestion != descSuggestions.last()) {
                                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                            }
                        }
                    }
                }
            }

            // Task 7: thousands-separator formatted amount field
            OutlinedTextField(
                value = amountDisplay,
                onValueChange = { raw ->
                    val (disp, digits) = formatAmountDisplay(raw)
                    amountDisplay = disp
                    amountStr = digits
                },
                label = { Text("Amount") },
                placeholder = { Text("0") },
                leadingIcon = {
                    Text(
                        LocalCurrency.current.symbol,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = FinanceShapes.Medium
            )

            // Issue 5 FIX: Category dropdown with proper click handling
            CategorySelector(
                selectedCategory = category,
                categories = categories,
                onCategoryChange = { category = it }
            )

            // Issue 5 FIX: Wallet Source dropdown with proper click handling
            WalletSourceSelector(
                selectedWalletSource = walletSource,
                wallets = wallets,
                onWalletSourceChange = { walletSource = it }
            )

            // Issue 4 FIX: Date/Time fields with .weight(1f) to prevent overflow
            // Issue 5 FIX: Clickable overlay on date/time fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Date field with clickable overlay
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = dateFormatter.format(selectedDate),
                        onValueChange = {},
                        readOnly = true,
                        enabled = false,
                        label = { Text("Date") },
                        trailingIcon = { Icon(Icons.Filled.CalendarMonth, contentDescription = "Date") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = FinanceShapes.Medium,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledBorderColor = MaterialTheme.colorScheme.outline,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    // Transparent overlay to capture clicks
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { showDatePicker() }
                    )
                }
                // Time field with clickable overlay
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = timeFormatter.format(selectedDate),
                        onValueChange = {},
                        readOnly = true,
                        enabled = false,
                        label = { Text("Time") },
                        trailingIcon = { Icon(Icons.Filled.AccessTime, contentDescription = "Time") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = FinanceShapes.Medium,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledBorderColor = MaterialTheme.colorScheme.outline,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { showTimePicker() }
                    )
                }
            }

            // Bottom Action Buttons
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = FinanceShapes.Medium
                    ) {
                        Text("Cancel", style = MaterialTheme.typography.labelLarge)
                    }
                    Button(
                        onClick = {
                            if (isSaving) return@Button
                            isSaving = true
                            val amt = amountStr.toDoubleOrNull() ?: return@Button
                            val finalAmount = if (type == Transaction.Type.EXPENSE) -amt else amt
                            val chosenWallet = if (walletSource.isNotBlank()) walletSource else (wallets.firstOrNull()?.name ?: "My Wallet")
                            // Issue 6 FIX: Use proper txId for update
                            val tx = Transaction(
                                id = if (isEditing) txId else 0,
                                description = description.trim(),
                                amount = finalAmount,
                                type = type,
                                category = category,
                                walletSource = chosenWallet,
                                date = selectedDate
                            )
                            scope.launch(Dispatchers.IO) {
                                if (isEditing) {
                                    repo.update(tx)
                                } else {
                                    repo.insert(tx)
                                }
                            }
                            navController.popBackStack()
                        },
                        enabled = canSave && !isSaving,
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = FinanceShapes.Medium,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Text(if (isEditing) "Update" else "Save", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.W600)
                    }
                }
            }
        }
    }
}

// =====================================================================
// TYPE SELECTOR
@Composable
fun TypeSelector(
    selectedType: Transaction.Type,
    onTypeChange: (Transaction.Type) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TypeButton(
            label = "Income",
            icon = Icons.Filled.TrendingUp,
            isSelected = selectedType == Transaction.Type.INCOME,
            selectedColor = FinanceColors.IncomeGreen,
            selectedContainerColor = FinanceColors.IncomeGreenContainer,
            onClick = { onTypeChange(Transaction.Type.INCOME) },
            modifier = Modifier.weight(1f)
        )
        TypeButton(
            label = "Expense",
            icon = Icons.Filled.TrendingDown,
            isSelected = selectedType == Transaction.Type.EXPENSE,
            selectedColor = FinanceColors.ExpenseRed,
            selectedContainerColor = FinanceColors.ExpenseRedContainer,
            onClick = { onTypeChange(Transaction.Type.EXPENSE) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun TypeButton(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    selectedColor: Color,
    selectedContainerColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = if (isSelected) {
        ButtonDefaults.buttonColors(
            containerColor = selectedColor,
            contentColor = Color.White
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp),
        shape = FinanceShapes.Large,
        colors = colors,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isSelected) 4.dp else 1.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = "", modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(label, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W600)
        }
    }
}

// =====================================================================
// CATEGORY SELECTOR - Issue 5 FIX: Clickable overlay instead of .clickable on TextField
// =====================================================================
@Composable
fun CategorySelector(
    selectedCategory: String,
    categories: List<Category>,
    onCategoryChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedCategory,
            onValueChange = {},
            readOnly = true,
            enabled = false,
            label = { Text("Category") },
            trailingIcon = {
                Icon(
                    Icons.Filled.ExpandMore,
                    contentDescription = "Category",
                    tint = if (expanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = FinanceShapes.Medium,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledTrailingIconColor = if (expanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        // Transparent overlay to capture clicks
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { cat ->
                val isSelected = cat.name == selectedCategory
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(
                                        if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceContainerHighest,
                                        RoundedCornerShape(18.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    CategoryIcons.getIcon(cat.name),
                                    contentDescription = cat.name,
                                    tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Text(
                                cat.name,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = if (isSelected) FontWeight.W600 else FontWeight.W400,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                            if (isSelected) {
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = "Selected",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    },
                    onClick = {
                        onCategoryChange(cat.name)
                        expanded = false
                    }
                )
            }
        }
    }
}

// =====================================================================
// WALLET SOURCE SELECTOR - Issue 5 FIX: Clickable overlay
// =====================================================================
@Composable
fun WalletSourceSelector(
    selectedWalletSource: String,
    wallets: List<Wallet>,
    onWalletSourceChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val displayValue = if (selectedWalletSource.isNotBlank()) selectedWalletSource else (wallets.firstOrNull()?.name ?: "No Wallet")

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = displayValue,
            onValueChange = {},
            readOnly = true,
            enabled = false,
            label = { Text("Wallet Source") },
            trailingIcon = {
                Icon(
                    Icons.Filled.ExpandMore,
                    contentDescription = "Wallet Source",
                    tint = if (expanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = FinanceShapes.Medium,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledTrailingIconColor = if (expanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            wallets.forEach { wallet ->
                val isSelected = wallet.name.equals(selectedWalletSource, ignoreCase = true)
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(
                                        if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceContainerHighest,
                                        RoundedCornerShape(18.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    getWalletIcon(wallet.type),
                                    contentDescription = wallet.name,
                                    tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Column {
                                Text(
                                    wallet.name,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = if (isSelected) FontWeight.W600 else FontWeight.W400,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    wallet.type.displayName,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (isSelected) {
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = "Selected",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    },
                    onClick = {
                        onWalletSourceChange(wallet.name)
                        expanded = false
                    }
                )
            }
        }
    }
}

// =====================================================================
// SETTINGS SCREEN
// =====================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    repo: FinanceRepository,
    shareIntentLauncher: androidx.activity.result.ActivityResultLauncher<Intent>,
    themePreference: MutableState<Int>,
    currency: MutableState<com.example.catatankeuangan.CurrencyInfo>,
    palettePreference: MutableState<com.example.catatankeuangan.PaletteInfo>,
    customBg: MutableState<Color?>,
    customBox: MutableState<Color?>,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val wallets by repo.allWallets.collectAsState(initial = emptyList())
    val allTransactions by repo.allTransactions.collectAsState(initial = emptyList())

    // Wallet management state
    var showAddWalletDialog by remember { mutableStateOf(false) }
    var showRenameWalletDialog by remember { mutableStateOf<Wallet?>(null) }
    var showDeleteWalletDialog by remember { mutableStateOf<Wallet?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Appearance section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = FinanceShapes.Large,
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                SettingsSection(title = "Appearance") {
                    SettingsItem(
                        title = "Theme",
                        subtitle = when (themePreference.value) {
                            0 -> "Light"
                            1 -> "Dark"
                            else -> "System Default"
                        },
                        leadingIcon = { Icon(Icons.Filled.Brightness6, contentDescription = "") },
                        trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                        onClick = {
                            // Issue 7 FIX: Show theme dialog that updates Compose state
                            val options = arrayOf("Light", "Dark", "System Default")
                            android.app.AlertDialog.Builder(context)
                                .setTitle("Choose Theme")
                                .setSingleChoiceItems(options, themePreference.value) { dialog, which ->
                                    themePreference.value = which
                                    context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                                        .edit()
                                        .putInt("theme_mode", which)
                                        .apply()
                                    dialog.dismiss()
                                }
                                .setNegativeButton("Cancel") { _, _ -> }
                                .show()
                        }
                    )
                }
            }
        }

        // Color Palette section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = FinanceShapes.Large,
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                SettingsSection(title = "Color Palette") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ColorPalette.palettes.chunked(4).forEach { row ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                row.forEach { p ->
                                    val selected = palettePreference.value.id == p.id
                                    Column(
                                        modifier = Modifier.weight(1f),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(48.dp)
                                                .clip(CircleShape)
                                                .background(p.primary)
                                                .clickable {
                                                    palettePreference.value = p
                                                    ColorPalette.save(context, p)
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (selected) {
                                                Icon(Icons.Filled.Check, contentDescription = "Selected", tint = p.onPrimary)
                                            }
                                        }
                                        Text(
                                            p.name,
                                            style = MaterialTheme.typography.labelSmall,
                                            maxLines = 1,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
                        TextButton(onClick = {
                            val def = ColorPalette.fromId("default")
                            palettePreference.value = def
                            ColorPalette.save(context, def)
                        }) {
                            Text("Reset to Default")
                        }
                        // Background color swatches (from ColorPalette)
                        Text("Background Color", style = MaterialTheme.typography.titleMedium)
                        val paletteColors = ColorPalette.palettes.map { it.backgroundTint ?: it.primaryContainer }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            paletteColors.take(5).forEach { c ->
                                val selected = customBg.value == c
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(c)
                                        .border(2.dp, if (selected) MaterialTheme.colorScheme.primary else Color.Gray, CircleShape)
                                        .clickable {
                                            customBg.value = c
                                            ColorCustomizerPrefs.saveBackgroundColor(context, c, themePreference.value == 1)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (selected) {
                                        Icon(Icons.Filled.Check, contentDescription = "Selected", tint = ColorCustomizerPrefs.getContrastTextColor(c), modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            paletteColors.drop(5).forEach { c ->
                                val selected = customBg.value == c
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(c)
                                        .border(2.dp, if (selected) MaterialTheme.colorScheme.primary else Color.Gray, CircleShape)
                                        .clickable {
                                            customBg.value = c
                                            ColorCustomizerPrefs.saveBackgroundColor(context, c, themePreference.value == 1)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (selected) {
                                        Icon(Icons.Filled.Check, contentDescription = "Selected", tint = ColorCustomizerPrefs.getContrastTextColor(c), modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                        }

                        // Box color swatches (from ColorPalette)
                        Text("Box Color", style = MaterialTheme.typography.titleMedium)
                        val boxPaletteColors = ColorPalette.palettes.map { it.cardContainerTint ?: Color.White }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            boxPaletteColors.take(5).forEach { c ->
                                val selected = customBox.value == c
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(c)
                                        .border(2.dp, if (selected) MaterialTheme.colorScheme.primary else Color.Gray, CircleShape)
                                        .clickable {
                                            customBox.value = c
                                            ColorCustomizerPrefs.saveBoxColor(context, c, themePreference.value == 1)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (selected) {
                                        Icon(Icons.Filled.Check, contentDescription = "Selected", tint = ColorCustomizerPrefs.getContrastTextColor(c), modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            boxPaletteColors.drop(5).forEach { c ->
                                val selected = customBox.value == c
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(c)
                                        .border(2.dp, if (selected) MaterialTheme.colorScheme.primary else Color.Gray, CircleShape)
                                        .clickable {
                                            customBox.value = c
                                            ColorCustomizerPrefs.saveBoxColor(context, c, themePreference.value == 1)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (selected) {
                                        Icon(Icons.Filled.Check, contentDescription = "Selected", tint = ColorCustomizerPrefs.getContrastTextColor(c), modifier = Modifier.size(18.dp))
                                    }
                                }
                            }
                        }
                        TextButton(onClick = {
                            customBg.value = null
                            context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                                .edit().remove("custom_bg_color").apply()
                        }) {
                            Text("Reset Background")
                        }
                        TextButton(onClick = {
                            customBox.value = null
                            context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                                .edit().remove("custom_box_color").apply()
                        }) {
                            Text("Reset Box")
                        }
                    }
                }
            }
        }

        // Issue 2: Wallet Management section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = FinanceShapes.Large,
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                SettingsSection(title = "Wallets") {
                    wallets.forEach { wallet ->
                        val txCount = allTransactions.count { it.walletSource.equals(wallet.name, ignoreCase = true) || (it.walletSource == "WALLET" && wallet.name == "My Wallet") }
                        SettingsItem(
                            title = wallet.name,
                            subtitle = "${wallet.type.displayName} • $txCount transactions",
                            leadingIcon = { Icon(getWalletIcon(wallet.type), contentDescription = "") },
                            trailingIcon = {
                                Row {
                                    IconButton(onClick = { showRenameWalletDialog = wallet }, modifier = Modifier.size(32.dp)) {
                                        Icon(Icons.Filled.Edit, contentDescription = "Rename", modifier = Modifier.size(16.dp))
                                    }
                                    IconButton(onClick = { showDeleteWalletDialog = wallet }, modifier = Modifier.size(32.dp)) {
                                        Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(16.dp))
                                    }
                                }
                            },
                            onClick = { showRenameWalletDialog = wallet }
                        )
                    }
                    SettingsItem(
                        title = "Add Wallet",
                        subtitle = "Create a new wallet source",
                        leadingIcon = { Icon(Icons.Filled.Add, contentDescription = "") },
                        trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                        onClick = { showAddWalletDialog = true }
                    )
                }
            }
        }

        // Task 8: Currency Selection
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = FinanceShapes.Large,
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                SettingsSection(title = "General") {
                    var showCurrencyDialog by remember { mutableStateOf(false) }
                    SettingsItem(
                        title = "Currency",
                        subtitle = "${currency.value.currencyName} (${currency.value.isoCode}) ${currency.value.symbol}",
                        leadingIcon = { Icon(Icons.Filled.Language, contentDescription = "") },
                        trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                        onClick = { showCurrencyDialog = true }
                    )
                    if (showCurrencyDialog) {
                        CurrencySelectionDialog(
                            current = currency.value,
                            onDismiss = { showCurrencyDialog = false },
                            onConfirm = { selected ->
                                currency.value = selected
                                CurrencyPreference.save(context, selected)
                                showCurrencyDialog = false
                            }
                        )
                    }
                }
            }
        }

        // Task 6: Google Drive Backup
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = FinanceShapes.Large,
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                SettingsSection(title = "Data — Google Drive Backup") {
                    val driveConnected = remember { mutableStateOf(false) }
                    val lastBackup = remember { mutableStateOf<String?>(null) }
                    if (!driveConnected.value) {
                        SettingsItem(
                            title = "Connect Google Drive",
                            subtitle = "Sign in to enable backup & restore",
                            leadingIcon = { Icon(Icons.Filled.CloudUpload, contentDescription = "") },
                            trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                            onClick = {
                                android.widget.Toast.makeText(
                                    context,
                                    "Google Drive: add google-services.json and OAuth credentials to enable this feature.",
                                    android.widget.Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                    } else {
                        SettingsItem(
                            title = "Back Up to Google Drive",
                            subtitle = lastBackup.value?.let { "Last backup: $it" } ?: "Never backed up",
                            leadingIcon = { Icon(Icons.Filled.CloudUpload, contentDescription = "") },
                            trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                            onClick = {
                                android.widget.Toast.makeText(context, "Backup started…", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        )
                        SettingsItem(
                            title = "Restore from Google Drive",
                            subtitle = "Replace current data from a backup",
                            leadingIcon = { Icon(Icons.Filled.CloudDownload, contentDescription = "") },
                            trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                            onClick = {
                                android.widget.Toast.makeText(context, "Select a backup to restore…", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        )
                        SettingsItem(
                            title = "Disconnect Google Drive",
                            subtitle = "Remove Drive access",
                            leadingIcon = { Icon(Icons.Filled.Close, contentDescription = "") },
                            trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                            onClick = { driveConnected.value = false },
                            isDestructive = true
                        )
                    }
                }
            }
        }

        // Data section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = FinanceShapes.Large,
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                SettingsSection(title = "Data") {
                    // CSV Export
                    SettingsItem(
                        title = "Export Data",
                        subtitle = "Export transactions to CSV",
                        leadingIcon = { Icon(Icons.Filled.FileDownload, contentDescription = "") },
                        trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                        onClick = { exportData(context, repo, scope) }
                    )
                    // CSV Import (Task 5 extension)
                    var showImportDialog by remember { mutableStateOf(false) }
                    var importedCsvText by remember { mutableStateOf<String?>(null) }
                    val csvPickerLauncher = rememberLauncherForActivityResult(
                        ActivityResultContracts.GetContent()
                    ) { uri: Uri? ->
                        uri?.let {
                            val text = context.contentResolver.openInputStream(it)
                                ?.bufferedReader(Charsets.UTF_8)?.readText()
                            if (text != null) {
                                importedCsvText = text
                                showImportDialog = true
                            } else {
                                android.widget.Toast.makeText(context, "Failed to read file", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    SettingsItem(
                        title = "Import from CSV",
                        subtitle = "Import transactions from exported CSV file",
                        leadingIcon = { Icon(Icons.Filled.FileUpload, contentDescription = "") },
                        trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                        onClick = { csvPickerLauncher.launch("text/*") }
                    )
                    if (showImportDialog && importedCsvText != null) {
                        CsvImportDialog(
                            csvText = importedCsvText!!,
                            repo = repo,
                            scope = scope,
                            onDismiss = { showImportDialog = false; importedCsvText = null }
                        )
                    }
                    SettingsItem(
                        title = "Clear All Data",
                        subtitle = "Delete all transactions and categories",
                        leadingIcon = { Icon(Icons.Filled.DeleteForever, contentDescription = "") },
                        trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                        onClick = { showClearDataDialog(context, repo, scope) },
                        isDestructive = true
                    )
                }
            }
        }

        // About section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = FinanceShapes.Large,
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                SettingsSection(title = "About") {
                    SettingsItem(
                        title = "Version",
                        subtitle = "1.0.0",
                        leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "") },
                        onClick = {}
                    )
                    SettingsItem(
                        title = "Financial Ledger",
                        subtitle = "Modern personal finance tracker",
                        leadingIcon = { Icon(Icons.Filled.AccountBalanceWallet, contentDescription = "") },
                        onClick = {}
                    )
                }
            }
        }
    }

    // Add Wallet Dialog
    if (showAddWalletDialog) {
        AddWalletDialog(
            existingNames = wallets.map { it.name },
            onDismiss = { showAddWalletDialog = false },
            onConfirm = { name, type ->
                scope.launch(Dispatchers.IO) {
                    repo.insertWallet(Wallet(name = name, type = type))
                }
                showAddWalletDialog = false
            }
        )
    }

    // Rename Wallet Dialog
    showRenameWalletDialog?.let { wallet ->
        RenameWalletDialog(
            wallet = wallet,
            existingNames = wallets.map { it.name },
            onDismiss = { showRenameWalletDialog = null },
            onConfirm = { newName ->
                val oldName = wallet.name
                scope.launch(Dispatchers.IO) {
                    repo.updateWallet(wallet.copy(name = newName), oldName = oldName)
                }
                showRenameWalletDialog = null
            }
        )
    }

    // Delete Wallet Dialog
    showDeleteWalletDialog?.let { wallet ->
        val txCount = allTransactions.count { it.walletSource.equals(wallet.name, ignoreCase = true) || (it.walletSource == "WALLET" && wallet.name == "My Wallet") }
        DeleteWalletDialog(
            wallet = wallet,
            transactionCount = txCount,
            onDismiss = { showDeleteWalletDialog = null },
            onConfirm = {
                scope.launch(Dispatchers.IO) {
                    repo.deleteWallet(wallet)
                }
                showDeleteWalletDialog = null
            }
        )
    }
}

// =====================================================================
// WALLET MANAGEMENT DIALOGS (Issue 2)
// =====================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWalletDialog(
    existingNames: List<String>,
    onDismiss: () -> Unit,
    onConfirm: (String, WalletType) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(WalletType.CASH) }
    var typeExpanded by remember { mutableStateOf(false) }
    val nameError = when {
        name.isBlank() -> "Name cannot be empty"
        existingNames.any { it.equals(name.trim(), ignoreCase = true) } -> "Wallet name already exists"
        else -> null
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Wallet") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Wallet Name") },
                    singleLine = true,
                    isError = name.isNotEmpty() && nameError != null,
                    supportingText = {
                        if (name.isNotEmpty() && nameError != null) {
                            Text(nameError, color = MaterialTheme.colorScheme.error)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = FinanceShapes.Medium
                )
                // Wallet type selector
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = selectedType.displayName,
                        onValueChange = {},
                        readOnly = true,
                        enabled = false,
                        label = { Text("Type") },
                        trailingIcon = { Icon(Icons.Filled.ExpandMore, contentDescription = "") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = FinanceShapes.Medium,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            disabledBorderColor = MaterialTheme.colorScheme.outline,
                            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    Box(modifier = Modifier.matchParentSize().clickable { typeExpanded = true })
                    DropdownMenu(
                        expanded = typeExpanded,
                        onDismissRequest = { typeExpanded = false }
                    ) {
                        WalletType.values().forEach { wt ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        Icon(getWalletIcon(wt), contentDescription = "", modifier = Modifier.size(20.dp))
                                        Text(wt.displayName)
                                    }
                                },
                                onClick = {
                                    selectedType = wt
                                    typeExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(name.trim(), selectedType) },
                enabled = name.trim().isNotEmpty() && nameError == null
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun RenameWalletDialog(
    wallet: Wallet,
    existingNames: List<String>,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var name by remember { mutableStateOf(wallet.name) }
    val nameError = when {
        name.isBlank() -> "Name cannot be empty"
        existingNames.any { it.equals(name.trim(), ignoreCase = true) && !it.equals(wallet.name, ignoreCase = true) } -> "Wallet name already exists"
        else -> null
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Rename Wallet") },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Wallet Name") },
                singleLine = true,
                isError = name.isNotEmpty() && nameError != null,
                supportingText = {
                    if (name.isNotEmpty() && nameError != null) {
                        Text(nameError, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = FinanceShapes.Medium
            )
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(name.trim()) },
                enabled = name.trim().isNotEmpty() && nameError == null
            ) {
                Text("Rename")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Composable
fun DeleteWalletDialog(
    wallet: Wallet,
    transactionCount: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Wallet") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (transactionCount > 0) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Filled.Warning, contentDescription = "", tint = MaterialTheme.colorScheme.error)
                        Text(
                            "This wallet has $transactionCount transaction(s). Deleting it will NOT remove those transactions, but they will no longer be linked to a wallet.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                } else {
                    Text("Delete wallet '${wallet.name}'?")
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

// =====================================================================
// CURRENCY SELECTION DIALOG (Task 8)
// =====================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencySelectionDialog(
    current: com.example.catatankeuangan.CurrencyInfo,
    onDismiss: () -> Unit,
    onConfirm: (com.example.catatankeuangan.CurrencyInfo) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val filtered = remember(query) {
        val q = query.lowercase()
        if (q.isBlank()) CurrencyPreference.currencies
        else CurrencyPreference.currencies.filter {
            it.countryName.lowercase().contains(q) ||
            it.currencyName.lowercase().contains(q) ||
            it.isoCode.lowercase().contains(q) ||
            it.symbol.contains(q)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Currency") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Search country or currency…") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = FinanceShapes.Medium
                )
                LazyColumn(
                    modifier = Modifier.heightIn(max = 300.dp),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    items(filtered) { info ->
                        val isSelected = info.isoCode == current.isoCode
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onConfirm(info) }
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                                    else androidx.compose.ui.graphics.Color.Transparent
                                )
                                .padding(horizontal = 8.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                info.symbol,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.W700,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.width(36.dp),
                                textAlign = TextAlign.Center
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    info.countryName,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = if (isSelected) FontWeight.W600 else FontWeight.W400,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    "${info.currencyName} · ${info.isoCode}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (isSelected) {
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = "Selected",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

// =====================================================================
// THEME PREFERENCE - Issue 7 FIX
// =====================================================================
@Composable
fun rememberThemePreference(context: Context): MutableState<Int> {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val initialValue = prefs.getInt("theme_mode", 2) // 0=Light, 1=Dark, 2=System
    return remember { mutableStateOf(initialValue) }
}

// =====================================================================
// DATA EXPORT & CLEAR
// =====================================================================
fun exportData(context: Context, repo: FinanceRepository, scope: kotlinx.coroutines.CoroutineScope) {
    scope.launch(Dispatchers.IO) {
        val transactions = repo.db.transactionDao().getAll()
        val csv = StringBuilder()
        csv.appendLine("ID,Description,Amount,Type,Category,Wallet Source,Date")
        transactions.forEach { tx ->
            csv.appendLine("${tx.id},${tx.description},${tx.amount},${tx.type},${tx.category},${tx.walletSource},${tx.date.time}")
        }

        val fileName = "financial_ledger_export_${java.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())}.csv"
        val resolver = context.contentResolver
        val contentValues = android.content.ContentValues().apply {
            put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(android.provider.MediaStore.MediaColumns.MIME_TYPE, "text/csv")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(android.provider.MediaStore.MediaColumns.RELATIVE_PATH, android.os.Environment.DIRECTORY_DOWNLOADS)
            }
        }
        val uri = resolver.insert(android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let { u ->
            resolver.openOutputStream(u)?.use { outputStream ->
                outputStream.write(csv.toString().toByteArray())
            }
            val handler = android.os.Handler(android.os.Looper.getMainLooper())
            handler.post {
                android.widget.Toast.makeText(context, "Exported to Downloads/$fileName", android.widget.Toast.LENGTH_LONG).show()
            }
        }
    }
}

// =====================================================================
// CSV IMPORT DIALOG (Task 5 — Import from CSV)
// CSV format matches exportData: ID,Description,Amount,Type,Category,Wallet Source,Date
// =====================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CsvImportDialog(
    csvText: String,
    repo: FinanceRepository,
    scope: kotlinx.coroutines.CoroutineScope,
    onDismiss: () -> Unit
) {
    data class ParsedRow(
        val description: String,
        val amount: Double,
        val type: Transaction.Type,
        val category: String,
        val walletSource: String,
        val date: java.util.Date,
        val fingerprint: String  // for duplicate detection
    )

    // Parse CSV
    val lines = csvText.lines().filter { it.isNotBlank() }
    val header = lines.firstOrNull()?.trim()
    val expectedHeader = "ID,Description,Amount,Type,Category,Wallet Source,Date"
    val headerOk = header?.startsWith("ID,") == true

    val parsed = mutableListOf<ParsedRow>()
    val errors = mutableListOf<String>()

    if (headerOk) {
        lines.drop(1).forEachIndexed { i, line ->
            val parts = line.split(",")
            if (parts.size < 7) { errors.add("Row ${i+2}: too few columns"); return@forEachIndexed }
            runCatching {
                val desc = parts[1].trim()
                val amount = parts[2].trim().toDouble()
                val type = if (parts[3].trim().uppercase().contains("INCOME"))
                    Transaction.Type.INCOME else Transaction.Type.EXPENSE
                val cat = parts[4].trim()
                val wallet = parts[5].trim()
                val date = java.util.Date(parts[6].trim().toLong())
                val fp = "${desc}|${amount}|${type}|${cat}|${wallet}|${date.time}"
                parsed.add(ParsedRow(desc, amount, type, cat, wallet, date, fp))
            }.onFailure { errors.add("Row ${i+2}: ${it.message}") }
        }
    }

    // State
    var importing by remember { mutableStateOf(false) }
    var importResult by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { if (!importing) onDismiss() },
        title = { Text("Import from CSV") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (!headerOk) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Warning, contentDescription = "", tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(20.dp))
                        Text("Unrecognized CSV format. Expected header:\n$expectedHeader",
                            color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                    }
                } else if (importResult != null) {
                    Text(importResult!!, style = MaterialTheme.typography.bodyMedium)
                } else {
                    Text("Found ${parsed.size} valid row(s)${if (errors.isNotEmpty()) ", ${errors.size} error(s)" else ""}.",
                        style = MaterialTheme.typography.bodyMedium)
                    if (errors.isNotEmpty()) {
                        Text(errors.take(5).joinToString("\n"),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error)
                    }
                    if (parsed.isNotEmpty()) {
                        Text("Duplicates: detected by description + amount + date. Existing matches will be skipped.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        },
        confirmButton = {
            if (headerOk && parsed.isNotEmpty() && importResult == null) {
                Button(
                    onClick = {
                        importing = true
                        scope.launch(Dispatchers.IO) {
                            val existing = repo.getAllTransactionsList()
                            val existingFps = existing.map {
                                "${it.description}|${it.amount}|${it.type}|${it.category}|${it.walletSource}|${it.date.time}"
                            }.toSet()
                            val toImport = parsed.filter { it.fingerprint !in existingFps }
                            val skipped = parsed.size - toImport.size
                            var succeeded = 0
                            var failed = 0
                            // Use DB transaction for atomicity
                            repo.db.runInTransaction {
                                toImport.forEach { row ->
                                    runCatching {
                                        val tx = Transaction(
                                            id = 0,
                                            description = row.description,
                                            amount = row.amount,
                                            type = row.type,
                                            category = row.category,
                                            walletSource = row.walletSource,
                                            date = row.date
                                        )
                                        // Must run blocking in the transaction lambda
                                        kotlinx.coroutines.runBlocking { repo.insert(tx) }
                                        succeeded++
                                    }.onFailure { failed++ }
                                }
                            }
                            val msg = "Import complete: $succeeded imported, $skipped duplicate(s) skipped" +
                                if (failed > 0) ", $failed failed." else "."
                            kotlinx.coroutines.withContext(Dispatchers.Main) {
                                importResult = msg
                                importing = false
                            }
                        }
                    },
                    enabled = !importing
                ) {
                    if (importing) CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                    else Text("Import ${parsed.size} rows")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = { if (!importing) onDismiss() }) {
                Text(if (importResult != null) "Close" else "Cancel")
            }
        }
    )
}

fun showClearDataDialog(context: Context, repo: FinanceRepository, scope: kotlinx.coroutines.CoroutineScope) {
    android.app.AlertDialog.Builder(context)
        .setTitle("Clear All Data")
        .setMessage("This will permanently delete all transactions and categories. This action cannot be undone.")
        .setPositiveButton("Delete") { _, _ ->
            scope.launch(Dispatchers.IO) {
                repo.db.transactionDao().deleteAll()
                repo.db.categoryDao().deleteAll()
                val defaultCategories = listOf(
                    Category("Food & Dining", android.R.drawable.ic_menu_mapmode),
                    Category("Transport", android.R.drawable.ic_menu_directions),
                    Category("Shopping", android.R.drawable.ic_menu_add),
                    Category("Entertainment", android.R.drawable.ic_menu_gallery),
                    Category("Salary", android.R.drawable.ic_menu_myplaces),
                    Category("Freelance", android.R.drawable.ic_menu_edit),
                    Category("Investment", android.R.drawable.ic_menu_today),
                    Category("General", android.R.drawable.ic_menu_info_details)
                )
                defaultCategories.forEach { repo.db.categoryDao().insert(it) }
            }
            val handler = android.os.Handler(android.os.Looper.getMainLooper())
            handler.post {
                android.widget.Toast.makeText(context, "All data cleared", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
        .setNegativeButton("Cancel") { _, _ -> }
        .show()
}

// =====================================================================
// SETTINGS UI COMPONENTS
// =====================================================================
@Composable
fun SettingsSection(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            title.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        content()
    }
}

@Composable
fun SettingsItem(
    title: String,
    subtitle: String = "",
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit = {},
    isDestructive: Boolean = false,
    onClick: () -> Unit
) {
    val titleColor = if (isDestructive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
    val subtitleColor = if (isDestructive) MaterialTheme.colorScheme.error.copy(alpha = 0.7f) else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(Color.Transparent, FinanceShapes.Medium)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier.size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    leadingIcon()
                }
                Column {
                    Text(title, style = MaterialTheme.typography.bodyLarge, color = titleColor)
                    if (subtitle.isNotBlank()) {
                        Text(subtitle, style = MaterialTheme.typography.bodySmall, color = subtitleColor)
                    }
                }
            }
            trailingIcon()
        }
    }
}

// =====================================================================
// MAIN APP COMPOSABLE
// Issues 1, 6, 7 FIXED here
// =====================================================================
@Composable
fun FinancialLedgerApp() {
    val context = LocalContext.current
    val repo = FinanceRepository.getInstance(context)
    val navController = rememberNavController()

    // Issue 7 FIX: Theme preference drives MaterialTheme colorScheme directly via Compose state
    val themePreference = rememberThemePreference(context)
        val isDark = themePreference.value == 1
        val palettePreference = remember { mutableStateOf(ColorPalette.load(context)) }
        val customBg = remember(isDark) { mutableStateOf(ColorCustomizerPrefs.loadBackgroundColor(context, isDark)) }
        val customBox = remember(isDark) { mutableStateOf(ColorCustomizerPrefs.loadBoxColor(context, isDark)) }
    val dynamicText = customBg.value?.let { ColorCustomizerPrefs.getContrastTextColor(it) }
    val colorScheme = when (themePreference.value) {
        0 -> LightColorScheme.copy(
            primary = palettePreference.value.primary,
            primaryContainer = palettePreference.value.primaryContainer,
            secondary = palettePreference.value.secondary,
            secondaryContainer = palettePreference.value.secondaryContainer,
            background = customBg.value ?: (palettePreference.value.backgroundTint ?: LightColorScheme.background),
            surface = customBox.value ?: (palettePreference.value.surfaceTint ?: LightColorScheme.surface),
            surfaceVariant = customBox.value ?: (palettePreference.value.cardContainerTint ?: LightColorScheme.surfaceVariant),
            surfaceContainer = customBox.value ?: (palettePreference.value.cardContainerTint ?: LightColorScheme.surfaceContainer),
            onSurface = dynamicText ?: LightColorScheme.onSurface,
            onSurfaceVariant = dynamicText ?: LightColorScheme.onSurfaceVariant,
            onPrimary = palettePreference.value.onPrimary,
            onPrimaryContainer = palettePreference.value.onPrimaryContainer,
            surfaceTint = palettePreference.value.primary
        )
        1 -> DarkColorScheme.copy(
            primary = palettePreference.value.primary,
            primaryContainer = palettePreference.value.primaryContainer,
            secondary = palettePreference.value.secondary,
            secondaryContainer = palettePreference.value.secondaryContainer,
            background = customBg.value ?: DarkColorScheme.background,
            surface = customBox.value ?: DarkColorScheme.surface,
            surfaceVariant = customBox.value ?: DarkColorScheme.surfaceVariant,
            surfaceContainer = customBox.value ?: DarkColorScheme.surfaceContainer,
            onSurface = dynamicText ?: DarkColorScheme.onSurface,
            onSurfaceVariant = dynamicText ?: DarkColorScheme.onSurfaceVariant,
            onPrimary = palettePreference.value.onPrimary,
            onPrimaryContainer = palettePreference.value.onPrimaryContainer,
            surfaceTint = palettePreference.value.primary
        )
        else -> {
            val isSystemDark = LocalConfiguration.current.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
            val base = if (isSystemDark) DarkColorScheme else LightColorScheme
            base.copy(
                primary = palettePreference.value.primary,
                primaryContainer = palettePreference.value.primaryContainer,
                secondary = palettePreference.value.secondary,
                secondaryContainer = palettePreference.value.secondaryContainer,
                background = customBg.value ?: base.background,
                surface = customBox.value ?: base.surface,
                surfaceVariant = customBox.value ?: base.surfaceVariant,
                surfaceContainer = customBox.value ?: base.surfaceContainer,
                onSurface = dynamicText ?: base.onSurface,
                onSurfaceVariant = dynamicText ?: base.onSurfaceVariant,
                onPrimary = palettePreference.value.onPrimary,
                onPrimaryContainer = palettePreference.value.onPrimaryContainer,
                surfaceTint = palettePreference.value.primary
            )
        }
    }

    // Task 8: Currency preference
    val currency = remember { mutableStateOf(CurrencyPreference.load(context)) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"
    val isTopLevel = currentRoute in listOf("home", "transactions", "analytics", "settings")

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = {
            CompositionLocalProvider(LocalCurrency provides currency.value) {
            // Issue 1 FIX: Scaffold with explicit containerColor matching surface,
            // no enableEdgeToEdge() in Activity, so no black bar behind nav
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                bottomBar = {
                    if (isTopLevel) {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        ) {
                            NavigationBarItem(
                                selected = currentRoute == "home",
                                onClick = {
                                    if (currentRoute != "home") {
                                        navController.navigate("home") {
                                            popUpTo("home") { inclusive = true }
                                            launchSingleTop = true
                                        }
                                    }
                                },
                                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                                label = { Text("Home") },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                )
                            )
                            NavigationBarItem(
                                selected = currentRoute == "transactions",
                                onClick = {
                                    if (currentRoute != "transactions") {
                                        navController.navigate("transactions") {
                                            popUpTo("home")
                                            launchSingleTop = true
                                        }
                                    }
                                },
                                icon = { Icon(Icons.Filled.Receipt, contentDescription = "Transactions") },
                                label = { Text("Transactions") },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                )
                            )
                            NavigationBarItem(
                                selected = currentRoute == "analytics",
                                onClick = {
                                    if (currentRoute != "analytics") {
                                        navController.navigate("analytics") {
                                            popUpTo("home")
                                            launchSingleTop = true
                                        }
                                    }
                                },
                                icon = { Icon(Icons.Filled.BarChart, contentDescription = "Analytics") },
                                label = { Text("Analytics") },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                )
                            )
                            NavigationBarItem(
                                selected = currentRoute == "settings",
                                onClick = {
                                    if (currentRoute != "settings") {
                                        navController.navigate("settings") {
                                            popUpTo("home")
                                            launchSingleTop = true
                                        }
                                    }
                                },
                                icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                                label = { Text("Settings") },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                },
                modifier = Modifier,
                floatingActionButton = {
                    if (currentRoute == "home" || currentRoute == "transactions") {
                        FloatingActionButton(
                            onClick = { navController.navigate("add_transaction/-1") },
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Add Transaction")
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(navController, startDestination = "home", modifier = Modifier.padding(innerPadding)) {
                    composable("home") { HomeScreen(repo = repo) }
                    composable("transactions") { TransactionsScreen(repo = repo, navController = navController) }
                    // Issue 6 FIX: Proper navArgument with NavType.StringType, parse as Int
                    composable(
                        route = "add_transaction/{txId}",
                        arguments = listOf(navArgument("txId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val txIdStr = backStackEntry.arguments?.getString("txId") ?: "-1"
                        val txId = txIdStr.toIntOrNull() ?: -1
                        AddTransactionScreen(
                            repo = repo,
                            navController = navController,
                            txId = txId
                        )
                    }
                    composable("analytics") { AnalyticsScreen(repo = repo, navController = navController) }
                                        composable(
                                            route = "category_details/{categoryName}/{txType}/{periodFilter}/{year}/{month}/{day}",
                                            arguments = listOf(
                                                navArgument("categoryName") { type = NavType.StringType },
                                                navArgument("txType") { type = NavType.StringType },
                                                navArgument("periodFilter") { type = NavType.StringType },
                                                navArgument("year") { type = NavType.IntType },
                                                navArgument("month") { type = NavType.IntType },
                                                navArgument("day") { type = NavType.IntType }
                                            )
                                        ) { backStackEntry ->
                                            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                                            val txTypeStr = backStackEntry.arguments?.getString("txType") ?: "EXPENSE"
                                            val periodFilter = backStackEntry.arguments?.getString("periodFilter") ?: "Monthly"
                                            val year = backStackEntry.arguments?.getInt("year") ?: 2026
                                            val month = backStackEntry.arguments?.getInt("month") ?: 1
                                            val day = backStackEntry.arguments?.getInt("day") ?: 1
                                            CategoryDetailsScreen(
                                                repo = repo,
                                                navController = navController,
                                                categoryName = categoryName,
                                                txType = txTypeStr,
                                                periodFilter = periodFilter,
                                                year = year,
                                                month = month,
                                                day = day
                                            )
                                        }
                                        composable("settings") {
                        val shareIntentLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
                        SettingsScreen(
                            repo = repo,
                            shareIntentLauncher = shareIntentLauncher,
                            themePreference = themePreference,
                            currency = currency,
                            palettePreference = palettePreference,
                            customBg = customBg,
                            customBox = customBox
                        )
                    }
                }
            }
            } // CompositionLocalProvider
        }
    )
}

// =====================================================================
// MAIN ACTIVITY
// Issue 1 FIX: Removed enableEdgeToEdge() to eliminate black bar
// =====================================================================
public class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = this
            FinancialLedgerApp()
            // Sync status bar color with background color
            LaunchedEffect(Unit) {
                val bg = ColorCustomizerPrefs.loadBackgroundColor(context, false)
                if (bg != null) {
                    window.statusBarColor = bg.toArgb()
                    WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = bg.luminance() > 0.5f
                }
            }
        }
    }
}
