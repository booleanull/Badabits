package com.booleanull.core_ui.component

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.booleanull.core_ui.data.Item

abstract class PlaceholderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var loading = true

    var data: List<Item> = mutableListOf()
        set(value) {
            field = value
            loading = false
            notifyDataSetChanged()
        }

    abstract fun onCreateItem(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onCreatePlaceholder(parent: ViewGroup, viewType: Int): PlaceholderViewHolder

    abstract fun onBindItem(holder: RecyclerView.ViewHolder, position: Int)

    final override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (loading) {
            onCreatePlaceholder(parent, viewType)
        } else {
            onCreateItem(parent, viewType)
        }
    }

    final override fun getItemCount(): Int {
        return if (loading) {
            PLACEHOLDER_SIZE
        } else {
            data.size
        }
    }

    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is PlaceholderViewHolder) {
            onBindItem(holder, position)
        } else {

        }
    }

    inner class PlaceholderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        const val PLACEHOLDER_SIZE = 3
    }
}