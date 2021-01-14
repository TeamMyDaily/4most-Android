package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.R
import org.mydaily.data.model.network.response.ResKeywordListGet
import org.mydaily.databinding.ItemMyPageCurrentKeywordBinding
import org.mydaily.databinding.ItemMyPageKeywordListBinding

class MyPageKeywordListAdapter : RecyclerView.Adapter<MyPageKeywordListAdapter.ViewHolder>() {

    private val _data = mutableListOf<ResKeywordListGet.Data>()
    var data: List<ResKeywordListGet.Data> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }


    private var clickListener:((name: String)-> Unit) ?= null
    fun setClickListener(listener: ((name: String)-> Unit)){
        this.clickListener = listener
    }

    inner class ViewHolder(private val binding: ItemMyPageKeywordListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ResKeywordListGet.Data) {
            binding.data = data
            if(data.isSelected){
                binding.ivPinIcon.setImageResource(R.drawable.ic_pin_on)
            }else {
                binding.ivPinIcon.setImageResource(R.drawable.ic_pin_off)
            }
            binding.ibMoreBtn.setOnClickListener {
                clickListener?.invoke(data.name)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPageKeywordListAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMyPageKeywordListBinding =
            ItemMyPageKeywordListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageKeywordListAdapter.ViewHolder, position: Int) {
        holder.bind(_data[position])
    }

    override fun getItemCount(): Int = _data.size
}