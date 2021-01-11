package org.mydaily.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.expandablelayout.ExpandableLayout
import org.mydaily.R
import org.mydaily.data.model.network.response.ResTaskGet

/**
 * Expandable Layout does not support Databinding
 */
class DailyExpandableAdapter : RecyclerView.Adapter<DailyExpandableAdapter.ViewHolder>() {

    private val _data = mutableListOf<ResTaskGet.Data>()
    var data: List<ResTaskGet.Data> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    private var addButtonListener: ((id: Int) -> Unit)? = null
    fun setAddButtonListener(listener: ((id: Int) -> Unit)) {
        this.addButtonListener = listener
    }

    private var taskClickListener: ((id: Int) -> Unit)? = null
    fun setTaskClickListener(listener: ((id: Int) -> Unit)) {
        this.taskClickListener = listener
    }

    private val dailyKeywordAdapter = listOf(
        DailyKeywordAdapter(), DailyKeywordAdapter(), DailyKeywordAdapter(), DailyKeywordAdapter()
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val expandableLayout: ExpandableLayout =
            itemView.findViewById(R.id.expandableLayout)
        private val parentLayout: View = expandableLayout.parentLayout
        private val secondLayout: View = expandableLayout.secondLayout

        fun bind(task: ResTaskGet.Data, position: Int) {
            parentLayout.findViewById<TextView>(R.id.tv_number_of_task).text =
                task.tasks.size.toString()
            parentLayout.findViewById<TextView>(R.id.tv_number).text = "0${position + 1}"
            parentLayout.findViewById<TextView>(R.id.tv_report_name).text = task.name
            parentLayout.setOnClickListener {
                if (expandableLayout.isExpanded) {
                    parentLayout.findViewById<ImageView>(R.id.iv_spinner).isSelected = false
                    expandableLayout.collapse()
                } else {
                    parentLayout.findViewById<ImageView>(R.id.iv_spinner).isSelected = true
                    expandableLayout.expand()
                }
            }
            parentLayout.findViewById<ImageButton>(R.id.ib_plus).setOnClickListener {
                addButtonListener?.invoke(task.totalKeywordId)
            }

            secondLayout.findViewById<RecyclerView>(R.id.rv_weekly_goal).apply {
                adapter = dailyKeywordAdapter[position]
                layoutManager = LinearLayoutManager(itemView.context)
                setHasFixedSize(true)
            }
            dailyKeywordAdapter[position].data = task.tasks
            dailyKeywordAdapter[position].setClickListener {
                taskClickListener?.invoke(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_daily_expandable, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_data[position], position)
    }

    override fun getItemCount(): Int = _data.size
}