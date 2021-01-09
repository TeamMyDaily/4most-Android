package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.databinding.ItemMyPageKeywordListBinding

class MyPageKeywordAdapter : RecyclerView.Adapter<MyPageKeywordAdapter.ViewHolder>() {

    private val _data = mutableListOf<String>()
    var data: List<String> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    private var listener: ((String)-> Unit) ?= null

    fun setClickListener(listener : (String)-> Unit) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ItemMyPageKeywordListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keyword: String, position: Int) {
            binding.number = "0" + (position + 1)
            binding.keyword = keyword
            binding.clParent.setOnClickListener {
                listener?.invoke(keyword)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMyPageKeywordListBinding =
            ItemMyPageKeywordListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_data[position], position)
    }

    override fun getItemCount(): Int = _data.size
}