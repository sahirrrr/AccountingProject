package com.msib.accountingproject

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msib.accountingproject.databinding.TableRowCashFlowBinding

class TableAdapter : RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    private var listCash = ArrayList<Int>()

    fun setData(newCashData: ArrayList<Int>?) {
        if (newCashData == null) return
        listCash.clear()
        listCash.addAll(newCashData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TableViewHolder {
        val binding = TableRowCashFlowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val data = listCash[position]
        holder.bind(data, position)
    }

    override fun getItemCount() = listCash.size

    inner class TableViewHolder(private val binding: TableRowCashFlowBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cash: Int, position: Int) {
            with(binding) {
                tvCashFlow.text = cash.toString()
                tvYear.text = (position + 1).toString()
            }
        }
    }
}