package org.mydaily.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.R
import org.mydaily.data.model.ReportListData
import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.data.model.network.response.ResReportGet
import org.mydaily.databinding.ReportListItemBinding

class ReportDetailAdapter(private val context: Context) : RecyclerView.Adapter<ReportDetailAdapter.ViewHolder>() {
    private lateinit var binding: ReportListItemBinding
    var data = mutableListOf<ResReportDetailGet.Data.Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.report_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportDetailAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ReportListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ResReportDetailGet.Data.Task) {
            val rate = data.satisfaction.toString()
            binding.dailydata = data
            binding.tvTaskRate.text = "태스크 만족도: " + rate + "점"
        }

    }
    fun setDetailList(DetailList: List<ResReportDetailGet.Data.Task>) {
        data = DetailList.toMutableList()
        notifyDataSetChanged()
    }
}
