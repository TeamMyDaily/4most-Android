package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.data.model.Goal
import org.mydaily.databinding.ItemKeywordGoalBinding

class GoalReportAdapter: RecyclerView.Adapter<GoalReportAdapter.ViewHolder>() {

    private val _data = mutableListOf<Goal>()
    var data : List<Goal> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    private var addButtonClickListener: ((Goal)-> Unit) ?= null
    private var goalClickListener: ((Goal)-> Unit) ?= null

    fun setAddButtonClickListener(listener : (Goal)-> Unit) {
        this.addButtonClickListener = listener
    }

    fun setGoalClickListener(listener : (Goal)-> Unit) {
        this.goalClickListener = listener
    }

    inner class ViewHolder(private val binding: ItemKeywordGoalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(goal: Goal) {
            binding.goal = goal

            binding.tvAddBtn.setOnClickListener {
                addButtonClickListener?.invoke(goal)
            }
            binding.clParent.setOnClickListener {
                goalClickListener?.invoke(goal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemKeywordGoalBinding = ItemKeywordGoalBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int = _data.size
}