package com.booleanull.main_feature_ui.component.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.booleanull.core_ui.widget.PlaceholderView
import com.booleanull.main_feature_ui.R
import com.booleanull.main_feature_ui.data.Habit
import kotlinx.android.synthetic.main.view_habit_preview.view.*

class HabitAdapter : RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    private var loading = true

    var data = listOf<Habit>()
        set(value) {
            field = value
            notifyDataSetChanged()
            loading = false
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_habit_preview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (loading) {
            6
        } else {
            data.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data.elementAtOrNull(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Habit?) {
            val placeholders = listOf<PlaceholderView>(
                itemView.placeholder,
                itemView.placeholderIcon
            )
            if (item == null) placeholders.forEach {
                it.start()
            }
            item?.let {
                itemView.tvTitle.text = it.title
                itemView.tvDate.text = it.date
                placeholders.forEach {
                    it.stop()
                }
            }
        }
    }
}