package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.R
import org.mydaily.data.model.domain.KeywordDefine
import org.mydaily.databinding.ItemImportantFourValueBinding
import org.mydaily.databinding.ItemKeywordDefineBinding

class KeywordAddAdapter: RecyclerView.Adapter<KeywordAddAdapter.ViewHolder>() {

    private val _data = mutableListOf<String>()
    var data: List<String> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemImportantFourValueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(keyword: String) {
            binding.keyword = keyword
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemImportantFourValueBinding = ItemImportantFourValueBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int = _data.size
}