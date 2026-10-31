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
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.Fastfood
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.example.catatankeuangan.ui.design.CategoryIcons
import com.example.catatankeuangan.ui.design.DarkColorScheme
import com.example.catatankeuangan.ui.design.FinanceColors
import com.example.catatankeuangan.ui.design.FinanceDateFormatter
import com.example.catatankeuangan.ui.design.FinanceFormatter
import com.example.catatankeuangan.Category
import com.example.catatankeuangan.FinanceRepository
import com.example.catatankeuangan.Transaction
import com.example.catatankeuangan.WalletSource
import com.example.catatankeuangan.ui.design.FinanceShapes
import com.example.catatankeuangan.ui.design.FinanceSpacing
import com.example.catatankeuangan.ui.design.LightColorScheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Typography
import androidx.compose.ui.Modifier.Companion
import androidx.compose.material3.Surface
import java.text.NumberFormat
import java.util.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(repo: FinanceRepository) {
    val allTransactions by repo.allTransactions.collectAsState(initial = emptyList())
    val income = allTransactions.filter { it.type == Transaction.Type.INCOME }.sumOf { it.amount }
    val expense = allTransactions.filter { it.type == Transaction.Type.EXPENSE }.sumOf { it.amount }
    val balance = income + expense
    
    val walletBalance by repo.walletBalance.collectAsState(initial = 0.0)
    val bankBalance by repo.bankBalance.collectAsState(initial = 0.0)
    val stockWalletBalance by repo.stockWalletBalance.collectAsState(initial = 0.0)
    
    val recentTransactions = allTransactions.reversed().take(5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GreetingHeader()
            BalanceCard(
                totalBalance = balance,
                walletBalance = walletBalance,
                bankBalance = bankBalance,
                stockWalletBalance = stockWalletBalance
            )
            SummaryRow(income = income, expense = expense)
            ChartCard(income = income, expense = expense)
            RecentTransactionsSection(
                transactions = recentTransactions,
                onSeeAll = { }
            )
        }
    }
}

@Composable
fun GreetingHeader() {
    val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    val greeting = when {
        hour < 12 -> "Good Morning"
        hour < 17 -> "Good Afternoon"
        else -> "Good Evening"
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
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
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(22.dp))
        ) {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun BalanceCard(
    totalBalance: Double,
    walletBalance: Double,
    bankBalance: Double,
    stockWalletBalance: Double
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
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                WalletSourceCard(
                    label = "Wallet",
                    amount = walletBalance,
                    icon = Icons.Filled.AccountBalanceWallet,
                    iconColor = FinanceColors.IncomeGreen,
                    containerColor = FinanceColors.IncomeGreenContainer,
                    modifier = Modifier.weight(1f)
                )
                WalletSourceCard(
                    label = "Bank",
                    amount = bankBalance,
                    icon = Icons.Filled.AccountBalance,
                    iconColor = FinanceColors.IncomeGreen,
                    containerColor = FinanceColors.IncomeGreenContainer,
                    modifier = Modifier.weight(1f)
                )
                WalletSourceCard(
                    label = "Stock Wallet",
                    amount = stockWalletBalance,
                    icon = Icons.Filled.TrendingUp,
                    iconColor = FinanceColors.IncomeGreen,
                    containerColor = FinanceColors.IncomeGreenContainer,
                    modifier = Modifier.weight(1f)
                )
            }
        }
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
    Card(
        modifier = modifier
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = FinanceShapes.Medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Box(
                    modifier = Modifier.size(28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = "", tint = iconColor, modifier = Modifier.size(18.dp))
                }
            }
            Text(
                FinanceFormatter.formatIDR(amount),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.W700,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun SummaryRow(income: Double, expense: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SummaryCard(
            label = "Income",
            amount = income,
            icon = Icons.Filled.TrendingUp,
            iconColor = FinanceColors.IncomeGreen,
            containerColor = FinanceColors.IncomeGreenContainer,
            modifier = Modifier.weight(1f)
        )
        SummaryCard(
            label = "Expense",
            amount = -expense,
            icon = Icons.Filled.TrendingDown,
            iconColor = FinanceColors.ExpenseRed,
            containerColor = FinanceColors.ExpenseRedContainer,
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
                Text(label, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
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
                color = MaterialTheme.colorScheme.onSurface
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
                    val centerTextColor = AndroidColor.argb(
                        MaterialTheme.colorScheme.onSurfaceVariant.alpha,
                        MaterialTheme.colorScheme.onSurfaceVariant.red,
                        MaterialTheme.colorScheme.onSurfaceVariant.green,
                        MaterialTheme.colorScheme.onSurfaceVariant.blue
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
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transactions) { tx ->
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
    val amountColor = if (isIncome) FinanceColors.IncomeGreen else FinanceColors.ExpenseRed
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
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier.size(44.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val iconColor = if (isIncome) FinanceColors.IncomeGreen else FinanceColors.ExpenseRed
                    val iconBgColor = if (isIncome) FinanceColors.IncomeGreenContainer else FinanceColors.ExpenseRedContainer
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
                            tx.walletSource.displayName,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Text(
                "$amountPrefix${FinanceFormatter.formatIDR(Math.abs(tx.amount))}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.W700,
                color = amountColor
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(repo: FinanceRepository, navController: NavHostController) {
    val allTransactions by repo.allTransactions.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Transactions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.W600) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            )
        )

        Box(modifier = Modifier.fillMaxSize()) {
            if (allTransactions.isEmpty()) {
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
                            "No transactions yet",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            "Start tracking your finances by adding a transaction",
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
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .padding(bottom = 100.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(allTransactions.reversed()) { tx ->
                        TransactionItemWithActions(
                            tx = tx,
                            onEdit = { navController.navigate("add_transaction/${tx.id}") },
                            onDelete = { scope.launch(Dispatchers.IO) { repo.delete(tx) } }
                        )
                    }
                }
            }

            FloatingActionButton(
                onClick = { navController.navigate("add_transaction/-1") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Transaction")
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
    val amountColor = if (isIncome) FinanceColors.IncomeGreen else FinanceColors.ExpenseRed
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
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier.size(44.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val iconColor = if (isIncome) FinanceColors.IncomeGreen else FinanceColors.ExpenseRed
                    val iconBgColor = if (isIncome) FinanceColors.IncomeGreenContainer else FinanceColors.ExpenseRedContainer
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
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(tx.category, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("•", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outlineVariant)
                        Text(FinanceDateFormatter.formatDateTime(tx.date), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("•", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outlineVariant)
                        Text(tx.walletSource.displayName, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "$amountPrefix${FinanceFormatter.formatIDR(Math.abs(tx.amount))}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W700,
                    color = amountColor
                )
                IconButton(onClick = onEdit) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    repo: FinanceRepository,
    navController: NavHostController,
    txId: Int
) {
    val context = LocalContext.current
    val categories by repo.allCategories.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    val isEditing = txId != -1
    val allTransactions by repo.allTransactions.collectAsState(initial = emptyList())
    val initialTx = if (isEditing) {
        allTransactions.find { it.id == txId }
    } else null

    var description by remember { mutableStateOf(initialTx?.description ?: "") }
    var amountStr by remember { mutableStateOf(Math.abs(initialTx?.amount ?: 0.0).toString()) }
    var type by remember { mutableStateOf(initialTx?.type ?: Transaction.Type.INCOME) }
    var category by remember { mutableStateOf(initialTx?.category ?: categories.firstOrNull()?.name ?: "General") }
    var walletSource by remember { mutableStateOf(initialTx?.walletSource ?: WalletSource.WALLET) }
    var selectedDate by remember { mutableStateOf(initialTx?.date ?: Date()) }

    val dateFormatter = remember { java.text.SimpleDateFormat("dd MMM yyyy", Locale("id", "ID")) }
    val timeFormatter = remember { java.text.SimpleDateFormat("HH:mm", Locale("id", "ID")) }

    val showDatePicker = {
        val calendar = java.util.Calendar.getInstance()
        calendar.time = selectedDate
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedDate = calendar.time
                Unit
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
                calendar.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(java.util.Calendar.MINUTE, minute)
                selectedDate = calendar.time
                Unit
            },
            calendar.get(java.util.Calendar.HOUR_OF_DAY),
            calendar.get(java.util.Calendar.MINUTE),
            true
        ).show()
    }

    val canSave = description.trim().isNotEmpty() && amountStr.toDoubleOrNull() != null && (amountStr.toDoubleOrNull() ?: 0.0) > 0

    Scaffold(
        topBar = {
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
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
                onValueChange = { description = it },
                label = { Text("Description") },
                placeholder = { Text("e.g., Lunch, Salary, Shopping") },
                leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = "") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = FinanceShapes.Medium
            )

            OutlinedTextField(
                value = amountStr,
                onValueChange = { amountStr = it.filter { it.isDigit() } },
                label = { Text("Amount (IDR)") },
                placeholder = { Text("0") },
                leadingIcon = {
                    Text("Rp", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W600, color = MaterialTheme.colorScheme.onSurfaceVariant)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = FinanceShapes.Medium
            )

            CategorySelector(
                selectedCategory = category,
                categories = categories,
                onCategoryChange = { category = it }
            )

            WalletSourceSelector(
                selectedWalletSource = walletSource,
                onWalletSourceChange = { walletSource = it }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = dateFormatter.format(selectedDate),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Date") },
                    trailingIcon = { Icon(Icons.Filled.CalendarMonth, contentDescription = "Date") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker() },
                    shape = FinanceShapes.Medium
                )
                OutlinedTextField(
                    value = timeFormatter.format(selectedDate),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Time") },
                    trailingIcon = { Icon(Icons.Filled.AccessTime, contentDescription = "Time") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showTimePicker() },
                    shape = FinanceShapes.Medium
                )
            }

            Spacer(modifier = Modifier.fillMaxHeight())

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = FinanceShapes.Medium
                ) {
                    Text("Cancel", style = MaterialTheme.typography.labelLarge)
                }
                Button(
                    onClick = {
                        val amt = amountStr.toDoubleOrNull() ?: return@Button
                        val finalAmount = if (type == Transaction.Type.EXPENSE) -amt else amt
                        val tx = Transaction(
                            id = initialTx?.id ?: 0,
                            description = description.trim(),
                            amount = finalAmount,
                            type = type,
                            category = category,
                            walletSource = walletSource,
                            date = selectedDate
                        )
                        scope.launch(Dispatchers.IO) {
                            if (isEditing) repo.update(tx) else repo.insert(tx)
                        }
                        navController.popBackStack()
                    },
                    enabled = canSave,
                    modifier = Modifier.weight(1f).height(56.dp),
                    shape = FinanceShapes.Medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Text("Save", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.W600)
                }
            }
        }
    }
}

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

@Composable
fun CategorySelector(
    selectedCategory: String,
    categories: List<Category>,
    onCategoryChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                label = { Text("Category") },
                trailingIcon = {
                    Icon(
                        Icons.Filled.ExpandMore,
                        contentDescription = "Category",
                        tint = if (expanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                shape = FinanceShapes.Medium
            )
            if (expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { expanded = false }
                )
            }
        }

        if (expanded) {
            DropdownContainer(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategoryClick = { catName ->
                    onCategoryChange(catName)
                    expanded = false
                }
            )
        }
    }
}

@Composable
fun WalletSourceSelector(
    selectedWalletSource: WalletSource,
    onWalletSourceChange: (WalletSource) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedWalletSource.displayName,
                onValueChange = {},
                readOnly = true,
                label = { Text("Wallet Source") },
                trailingIcon = {
                    Icon(
                        Icons.Filled.ExpandMore,
                        contentDescription = "Wallet Source",
                        tint = if (expanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                shape = FinanceShapes.Medium
            )
            if (expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { expanded = false }
                )
            }
        }

        if (expanded) {
            WalletSourceDropdownContainer(
                selectedWalletSource = selectedWalletSource,
                onWalletSourceClick = { source ->
                    onWalletSourceChange(source)
                    expanded = false
                }
            )
        }
    }
}

@Composable
fun WalletSourceDropdownContainer(
    selectedWalletSource: WalletSource,
    onWalletSourceClick: (WalletSource) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        WalletSource.values().forEach { source ->
            val isSelected = source == selectedWalletSource
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primaryContainer
                        else Color.Transparent,
                        FinanceShapes.Medium
                    )
                    .clickable { onWalletSourceClick(source) }
            ) {
                Box(
                    modifier = Modifier.size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val iconColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    val iconBgColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceContainerHighest
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(iconBgColor, RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            when (source) {
                                WalletSource.WALLET -> Icons.Filled.AccountBalanceWallet
                                WalletSource.BANK -> Icons.Filled.AccountBalance
                                WalletSource.STOCK_WALLET -> Icons.Filled.TrendingUp
                            },
                            contentDescription = source.displayName,
                            tint = iconColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    source.displayName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = if (isSelected) FontWeight.W600 else FontWeight.W400,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.weight(1f))
                if (isSelected) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Selected",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DropdownContainer(
    categories: List<Category>,
    selectedCategory: String,
    onCategoryClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        categories.forEach { cat ->
            val isSelected = cat.name == selectedCategory
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primaryContainer
                        else Color.Transparent,
                        FinanceShapes.Medium
                    )
                    .clickable { onCategoryClick(cat.name) }
            ) {
                Box(
                    modifier = Modifier.size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val iconColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    val iconBgColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceContainerHighest
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(iconBgColor, RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            CategoryIcons.getIcon(cat.name),
                            contentDescription = cat.name,
                            tint = iconColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    cat.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = if (isSelected) FontWeight.W600 else FontWeight.W400,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.weight(1f))
                if (isSelected) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Selected",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(repo: FinanceRepository, shareIntentLauncher: androidx.activity.result.ActivityResultLauncher<Intent>) {
    val context = LocalContext.current
    val themePreference = rememberThemePreference(context)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
                        onClick = { showThemeDialog(context, themePreference) }
                    )
                }
                SettingsSection(title = "Data") {
                    SettingsItem(
                        title = "Export Data",
                        subtitle = "Export transactions to CSV",
                        leadingIcon = { Icon(Icons.Filled.FileDownload, contentDescription = "") },
                        trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                        onClick = { exportData(context, repo, scope) }
                    )
                    SettingsItem(
                        title = "Clear All Data",
                        subtitle = "Delete all transactions and categories",
                        leadingIcon = { Icon(Icons.Filled.DeleteForever, contentDescription = "") },
                        trailingIcon = { Icon(Icons.Filled.ChevronRight, contentDescription = "") },
                        onClick = { showClearDataDialog(context, repo, scope) },
                        isDestructive = true
                    )
                }
                SettingsSection(title = "About") {
                    SettingsItem(
                        title = "Version",
                        subtitle = "1.0.0",
                        leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "") },
                        onClick = {}
                    )
                    SettingsItem(
                        title = "FinancialLedger",
                        subtitle = "Modern personal finance tracker",
                        leadingIcon = { Icon(Icons.Filled.AccountBalanceWallet, contentDescription = "") },
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
fun rememberThemePreference(context: Context): MutableState<Int> {
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val initialValue = prefs.getInt("theme_mode", 2) // 0=Light, 1=Dark, 2=System
    return remember { mutableStateOf(initialValue) }
}

fun showThemeDialog(context: Context, themePreference: MutableState<Int>) {
    val options = arrayOf("Light", "Dark", "System Default")
    android.app.AlertDialog.Builder(context)
        .setTitle("Choose Theme")
        .setSingleChoiceItems(options, themePreference.value) { _, which ->
            themePreference.value = which
            context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                .edit()
                .putInt("theme_mode", which)
                .apply()
            // Apply theme change
            val activity = context as? androidx.appcompat.app.AppCompatActivity
            activity?.let {
                when (which) {
                    0 -> it.delegate.setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO)
                    1 -> it.delegate.setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES)
                    else -> it.delegate.setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }
        .setPositiveButton("OK") { _, _ -> }
        .show()
}

fun exportData(context: Context, repo: FinanceRepository, scope: kotlinx.coroutines.CoroutineScope) {
    scope.launch(Dispatchers.IO) {
        val transactions = repo.db.transactionDao().getAll()
        val csv = StringBuilder()
        csv.appendLine("ID,Description,Amount,Type,Category,Wallet Source,Date")
        transactions.forEach { tx ->
            csv.appendLine("${tx.id},${tx.description},${tx.amount},${tx.type},${tx.category},${tx.walletSource.displayName},${tx.date.time}")
        }
        
        // Save to Downloads folder
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
        uri?.let { uri ->
            resolver.openOutputStream(uri)?.use { outputStream ->
                outputStream.write(csv.toString().toByteArray())
            }
            val handler = android.os.Handler(android.os.Looper.getMainLooper())
            handler.post {
                android.widget.Toast.makeText(context, "Exported to Downloads/$fileName", android.widget.Toast.LENGTH_LONG).show()
            }
        }
    }
}

fun showClearDataDialog(context: Context, repo: FinanceRepository, scope: kotlinx.coroutines.CoroutineScope) {
    android.app.AlertDialog.Builder(context)
        .setTitle("Clear All Data")
        .setMessage("This will permanently delete all transactions and categories. This action cannot be undone.")
        .setPositiveButton("Delete") { _, _ ->
            scope.launch(Dispatchers.IO) {
                repo.db.transactionDao().deleteAll()
                repo.db.categoryDao().deleteAll()
                // Re-seed default categories
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
                verticalAlignment = Alignment.CenterVertically
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
