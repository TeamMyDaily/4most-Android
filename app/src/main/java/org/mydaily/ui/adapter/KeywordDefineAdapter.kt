package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.data.model.domain.KeywordPriority
import org.mydaily.databinding.ItemKeywordDefineBinding


class KeywordDefineAdapter: RecyclerView.Adapter<KeywordDefineAdapter.ViewHolder>() {

    private val _data = mutableListOf<KeywordPriority>()
    var data: List<KeywordPriority> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    private var listener: ((String)-> Unit) ?= null

    fun setClickListener(listener : (String)-> Unit) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ItemKeywordDefineBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(keywordPriority: KeywordPriority, position: Int) {
            binding.number = "0" + (position + 1)
            binding.keyword = keywordPriority.name
            binding.clParent.setOnClickListener {
                listener?.invoke(keywordPriority.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemKeywordDefineBinding = ItemKeywordDefineBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_data[position], position)
    }

    override fun getItemCount(): Int = _data.size
}
