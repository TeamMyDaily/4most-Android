package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.data.model.domain.KeywordPriority
import org.mydaily.databinding.ItemKeywordPriorityBinding
import java.util.*

class KeywordPriorityAdapter : RecyclerView.Adapter<KeywordPriorityAdapter.ViewHolder>(), ItemTouchHelperListener {

    private val _data = mutableListOf<KeywordPriority>()
    var data: List<KeywordPriority> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemKeywordPriorityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keywordPriority: KeywordPriority) {
            binding.keywordPriority = keywordPriority
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemKeywordPriorityBinding =
            ItemKeywordPriorityBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int = _data.size

    override fun onItemMoved(from: Int, to: Int){
        Collections.swap(_data, from, to)
        for(i in 0 until _data.size){
            _data[i].priority = i+1
        }
        notifyItemMoved(from, to)
    }
}