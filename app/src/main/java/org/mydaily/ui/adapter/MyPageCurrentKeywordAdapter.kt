package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.data.model.network.response.ResTaskKeywordGet
import org.mydaily.databinding.ItemMyPageCurrentKeywordBinding

class MyPageCurrentKeywordAdapter : RecyclerView.Adapter<MyPageCurrentKeywordAdapter.ViewHolder>() {

    private val _data = mutableListOf<ResTaskKeywordGet.Data.Keyword>()
    var data: List<ResTaskKeywordGet.Data.Keyword> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemMyPageCurrentKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(keyword: ResTaskKeywordGet.Data.Keyword, position: Int) {
            binding.keyword = keyword
            binding.number = (position + 1).toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageCurrentKeywordAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMyPageCurrentKeywordBinding =
            ItemMyPageCurrentKeywordBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageCurrentKeywordAdapter.ViewHolder, position: Int) {
        holder.bind(_data[position], position)
    }

    override fun getItemCount(): Int = _data.size
}