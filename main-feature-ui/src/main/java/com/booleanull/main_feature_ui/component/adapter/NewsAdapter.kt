package com.booleanull.main_feature_ui.component.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.booleanull.core_ui.widget.PlaceholderView
import com.booleanull.main_feature_ui.R
import com.booleanull.main_feature_ui.data.News
import kotlinx.android.synthetic.main.view_list_info.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var data: List<News> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_list_info,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (data[position] as? News)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return if (data.isEmpty()) {
            3
        } else {
            data.size
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: News) {
            val placeholders = mutableListOf<PlaceholderView>(
                itemView.placeholder,
                itemView.placeholderTitle,
                itemView.placeholderLogo
            )
            itemView.tvTitleInfo.text = item.title
            placeholders.forEach {
                it.start()
            }
        }
    }
}