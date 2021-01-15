package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.R
import org.mydaily.data.model.domain.KeywordDefine
import org.mydaily.databinding.ItemKeywordDefineBinding


class KeywordDefineAdapter: RecyclerView.Adapter<KeywordDefineAdapter.ViewHolder>() {

    private val _data = mutableListOf<KeywordDefine>()
    var data: List<KeywordDefine> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    private var keywordExistListener: ((KeywordDefine, Int)-> Unit) ?= null
    private var keywordNotExistListener: ((Int)-> Unit) ?= null

    fun setKeywordExistListener(listener : ((KeywordDefine, Int)-> Unit)) {
        this.keywordExistListener = listener
    }

    fun setKeywordNotExistListener(listener : ((Int)-> Unit)) {
        this.keywordNotExistListener = listener
    }

    inner class ViewHolder(private val binding: ItemKeywordDefineBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(keyword: KeywordDefine, position: Int) {
            val imageResource = if(keyword.isKeywordDefine){
                when(position){
                    0 -> R.drawable.ic_01_on
                    1 -> R.drawable.ic_02_on
                    2 -> R.drawable.ic_03_on
                    else -> R.drawable.ic_04_on
                }
            }else {
                when(position){
                    0 -> R.drawable.ic_01
                    1 -> R.drawable.ic_02
                    2 -> R.drawable.ic_03
                    else -> R.drawable.ic_04
                }
            }
            binding.ivNumber.setImageResource(imageResource)

            binding.isKeywordDefine = keyword.isKeywordDefine
            binding.keyword = keyword.name
            binding.clParent.setOnClickListener {
                //키워드 정의없을때만 클릭 가능
                if(!keyword.isKeywordDefine){
                    keywordExistListener?.invoke(keyword, position)
                } else {
                    keywordNotExistListener?.invoke(position)
                }
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
