package com.booleanull.main_feature_ui.component.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.booleanull.core_ui.component.PlaceholderAdapter
import com.booleanull.main_feature_ui.R
import com.booleanull.main_feature_ui.data.News
import kotlinx.android.synthetic.main.view_list_info.view.*

class NewsAdapter : PlaceholderAdapter() {

    override fun onCreateItem(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_list_info,
                parent,
                false
            )
        )
    }

    override fun onCreatePlaceholder(parent: ViewGroup, viewType: Int): PlaceholderViewHolder {
        return PlaceholderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_list_info_placeholder,
                parent,
                false
            )
        )
    }

    override fun onBindItem(holder: RecyclerView.ViewHolder, position: Int) {
        (data[position] as? News)?.let {
            (holder as ViewHolder).bind(it)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: News) {
            itemView.tvTitleInfo.text = item.title
        }
    }
}