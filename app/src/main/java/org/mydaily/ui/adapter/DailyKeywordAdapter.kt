package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.data.model.Keyword
import org.mydaily.databinding.ItemDailyGoalBinding

class DailyKeywordAdapter : RecyclerView.Adapter<DailyKeywordAdapter.ViewHolder>() {

    private val _data = mutableListOf<Keyword>()
    var data : List<Keyword> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDailyGoalBinding = ItemDailyGoalBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int = _data.size

    inner class ViewHolder(private val binding: ItemDailyGoalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(keyword: Keyword) {
            binding.keyword = keyword
        }
    }
}