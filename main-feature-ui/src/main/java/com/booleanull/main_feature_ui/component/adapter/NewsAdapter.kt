package com.booleanull.main_feature_ui.component.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.booleanull.core_ui.widget.PlaceholderView
import com.booleanull.main_feature_ui.R
import com.booleanull.main_feature_ui.data.News
import kotlinx.android.synthetic.main.view_list_info.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var loading = true

    var data = listOf<News>()
        set(value) {
            field = value
            notifyDataSetChanged()
            loading = false
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
        holder.bind(data.elementAtOrNull(position))
    }

    override fun getItemCount(): Int {
        return if (loading) {
            3
        } else {
            data.size
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: News?) {
            val placeholders = listOf<PlaceholderView>(
                itemView.placeholder,
                itemView.placeholderTitle,
                itemView.placeholderLogo
            )
            if (item == null) placeholders.forEach {
                it.start()
            }

            item?.let {
                itemView.tvTitle.text = item.title
                itemView.ivIcon.isVisible = item.icon
                item.image?.let {
                    itemView.ivBackground.isVisible = item.image
                }
                item.color?.let {
                    itemView.tvTitle.setTextColor(it)
                    itemView.ivIcon.imageTintList = ColorStateList.valueOf(it)
                }
                placeholders.forEach {
                    it.stop()
                }
            }
        }
    }
}