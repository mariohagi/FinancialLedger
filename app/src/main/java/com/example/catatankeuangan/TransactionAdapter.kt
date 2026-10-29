package com.example.catatankeuangan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catatankeuangan.databinding.ItemTransactionBinding

class TransactionAdapter(private val items: List<Transaction>) : RecyclerView.Adapter<TransactionAdapter.VH>() {
    inner class VH(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val tx = items[position]
        holder.binding.tvDescription.text = tx.description
        holder.binding.tvAmount.text = "${'$'}{tx.amount}"
        // colour based on type
        val color = if (tx.type == Transaction.Type.INCOME) android.graphics.Color.parseColor("#4CAF50")
                    else android.graphics.Color.parseColor("#F44336")
        holder.binding.tvAmount.setTextColor(color)
    }

    override fun getItemCount(): Int = items.size
}
