package com.example.catatankeuangan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catatankeuangan.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.*
import androidx.room.Room
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val transactions = mutableListOf<Transaction>()
    private lateinit var adapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        adapter = TransactionAdapter(transactions)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Example data (in real app load from DB)
        loadSampleData()
        updateChart()
    }

    private fun loadSampleData() {
        transactions.add(Transaction("Salary", 5000.0, Transaction.Type.INCOME))
        transactions.add(Transaction("Coffee", -4.5, Transaction.Type.EXPENSE))
        transactions.add(Transaction("Groceries", -150.0, Transaction.Type.EXPENSE))
        transactions.add(Transaction("Freelance", 1200.0, Transaction.Type.INCOME))
        adapter.notifyDataSetChanged()
    }

    private fun updateChart() {
        val income = transactions.filter { it.type == Transaction.Type.INCOME }.sumOf { it.amount }
        val expense = transactions.filter { it.type == Transaction.Type.EXPENSE }.sumOf { it.amount }
        val entries = listOf(
            PieEntry(income.toFloat(), "Income"),
            PieEntry((-expense).toFloat(), "Expense")
        )
        val dataSet = PieDataSet(entries, "Summary")
        dataSet.colors = listOf(android.graphics.Color.parseColor("#4CAF50"), android.graphics.Color.parseColor("#F44336"))
        val data = PieData(dataSet)
        binding.pieChart.data = data
        binding.pieChart.invalidate()
    }
}
