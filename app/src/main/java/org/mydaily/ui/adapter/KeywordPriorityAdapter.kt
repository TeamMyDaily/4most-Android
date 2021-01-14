package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.data.model.domain.KeywordPriority
import org.mydaily.databinding.ItemKeywordPriorityBinding
import java.util.*

class KeywordPriorityAdapter : RecyclerView.Adapter<KeywordPriorityAdapter.ViewHolder>(), ItemTouchHelperListener {

    private val _data = mutableListOf<String>()
    var data: List<String> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemKeywordPriorityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keyword: String) {
            binding.keyword = keyword
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

    override fun onItemMoved(fromPos: Int, toPos: Int){
        if (fromPos < toPos) {
            for(i in fromPos until toPos){
                Collections.swap(_data, i, i+1);
            }
        }else{
            for(i in fromPos downTo toPos+1){
                Collections.swap(_data, i, i-1);
            }
        }

        notifyItemMoved(fromPos, toPos)
    }
}