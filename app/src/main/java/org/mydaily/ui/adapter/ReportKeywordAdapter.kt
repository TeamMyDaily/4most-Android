package org.mydaily.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.R
import org.mydaily.data.model.ReportListData
import org.mydaily.databinding.KeywordListItemBinding
import org.mydaily.ui.view.remind.ReportFragment

class ReportKeywordAdapter(private val context: Context) : RecyclerView.Adapter<ReportKeywordAdapter.ViewHolder>() {
    private lateinit var binding: KeywordListItemBinding
    var data = mutableListOf<ReportListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.keyword_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: KeywordListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ReportListData) {
            val tasknum = data.task_num.toString()
            binding.reportlistdata = data
            binding.tvTasknum.text = "총 "+tasknum+"개"
            binding.tvRate.text = data.rate.toString()
            if(data.rate > 0) {
                binding.tvRate.setTextColor(Color.parseColor("#EC684A"))
            }
            else {
                binding.tvRate.setTextColor(Color.parseColor("#E5E5E5"))
            }
            binding.sbRate.setProgress(((data.rate * 10).toInt()),true)

            binding.sbRate.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    return true
                }
            })//프로그레스바 터치 prevent

        }
    }
}