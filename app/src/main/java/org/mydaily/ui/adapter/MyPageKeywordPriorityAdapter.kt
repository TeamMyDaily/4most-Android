package org.mydaily.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.data.model.domain.KeywordPriority
import org.mydaily.data.model.network.request.ReqKeywordPriority
import org.mydaily.databinding.ItemKeywordPriorityBinding
import org.mydaily.databinding.ItemMyPageKeywordPriorityBinding
import java.util.*

class MyPageKeywordPriorityAdapter : RecyclerView.Adapter<MyPageKeywordPriorityAdapter.ViewHolder>(), ItemTouchHelperListener {

    private val _data = mutableListOf<String>()
    var data: List<String> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemMyPageKeywordPriorityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) {
            binding.keyword = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMyPageKeywordPriorityBinding =
            ItemMyPageKeywordPriorityBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int = _data.size

    override fun onItemMoved(from: Int, to: Int){
        Collections.swap(_data, from, to)
        notifyItemMoved(from, to)
        Log.e("SEULGI MyPageKeywordPriorityAdapter", _data.toString())
    }
}