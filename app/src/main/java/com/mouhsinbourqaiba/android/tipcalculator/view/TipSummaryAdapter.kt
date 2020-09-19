package com.mouhsinbourqaiba.android.tipcalculator.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mouhsinbourqaiba.android.tipcalculator.R
import com.mouhsinbourqaiba.android.tipcalculator.databinding.SaveTipCalculationsListItemBindingImpl
import com.mouhsinbourqaiba.android.tipcalculator.viewmodel.TipCalculationSummaryItem

class TipSummaryAdapter(val onItemSelected: (item: TipCalculationSummaryItem) -> Unit): RecyclerView.Adapter<TipSummaryAdapter.TipSummaryViewHolder>() {

    private val tipCalculationSummaries = mutableListOf<TipCalculationSummaryItem>()

    fun updateList(updates: List<TipCalculationSummaryItem>) {
        tipCalculationSummaries.clear()
        tipCalculationSummaries.addAll(updates)
        notifyDataSetChanged()
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipSummaryViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<SaveTipCalculationsListItemBindingImpl>(inflate,
                R.layout.save_tip_calculations_list_item,
                parent, false)

            return TipSummaryViewHolder(binding)
        }

    override fun onBindViewHolder(holder: TipSummaryViewHolder, position: Int) {
        holder.bind(tipCalculationSummaries[position])
    }

    override fun getItemCount(): Int {
        return tipCalculationSummaries.size
    }

    inner class TipSummaryViewHolder(val binding: SaveTipCalculationsListItemBindingImpl):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TipCalculationSummaryItem) {
            binding.item = item
            binding.root.setOnClickListener { onItemSelected(item) }
            binding.executePendingBindings()
        }


    }


}