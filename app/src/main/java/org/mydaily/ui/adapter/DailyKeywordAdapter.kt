package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.data.model.Keyword
import org.mydaily.data.model.domain.Task
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.databinding.ItemDailyGoalBinding

class DailyKeywordAdapter : RecyclerView.Adapter<DailyKeywordAdapter.ViewHolder>() {

    private val _data = mutableListOf<ResTaskGet.Data.Task>()
    var data : List<ResTaskGet.Data.Task> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }


    private var clickListener:((id: Int)-> Unit) ?= null
    fun setClickListener(listener: ((id: Int)-> Unit)){
        this.clickListener = listener
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

        fun bind(task: ResTaskGet.Data.Task) {
            binding.task = task.title
            binding.tvKeyword.setOnClickListener {
                clickListener?.invoke(task.id)
            }
        }
    }
}