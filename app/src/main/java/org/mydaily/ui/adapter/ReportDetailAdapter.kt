package org.mydaily.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.mydaily.R
import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.databinding.ItemReportListBinding
import org.mydaily.ui.view.task.detail.TaskDetailActivity
import java.text.SimpleDateFormat

class ReportDetailAdapter(private val context: Context, private val keyword: String) : RecyclerView.Adapter<ReportDetailAdapter.ViewHolder>() {
    private lateinit var binding: ItemReportListBinding
    var data = mutableListOf<ResReportDetailGet.Data.Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_report_list, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportDetailAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemReportListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ResReportDetailGet.Data.Task) {
            val rate = data.satisfaction.toString()
            binding.dailydata = data
            binding.tvDate.text = dateConvert(data.date.substring(0,10))
            binding.tvTaskRate.text = "태스크 만족도: " + rate + "점"

            binding.clRecord.setOnClickListener {
                context.apply {
                    val intent = Intent(this, TaskDetailActivity::class.java).apply {
                        putExtra("taskId", data.taskId)
                        putExtra("keywordName", keyword)
                    }
                    startActivity(intent)
                }
            }
        }

        private fun dateConvert(date : String): String {
            val from = SimpleDateFormat("yyyy-MM-dd").parse(date)
            return SimpleDateFormat("yyyy. MM. dd").format(from)
        }
    }

    fun setDetailList(DetailList: List<ResReportDetailGet.Data.Task>) {
        data = DetailList.toMutableList()
        notifyDataSetChanged()
    }
}
