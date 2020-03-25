package com.booleanull.main_feature_ui.component.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.booleanull.core_ui.component.ChildBaseFragment
import com.booleanull.main_feature_ui.R
import com.booleanull.main_feature_ui.component.adapter.NewsAdapter
import com.booleanull.main_feature_ui.data.News
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_list.*
import kotlin.math.abs
import kotlin.math.min

class ListFragment : ChildBaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appbar.addOnOffsetChangedListener(appBarLayoutChanged)

        val testList = mutableListOf<News>()
        for (i in 0..2) {
            testList.add(News(i, "String $i"))
        }
        newsRecyclerView.adapter = NewsAdapter().apply {
            data = testList
        }
        newsRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = requireContext().resources.displayMetrics.density.toInt() * 16
                }
                outRect.right = requireContext().resources.displayMetrics.density.toInt() * 16
            }
        })
    }

    private val appBarLayoutChanged =
        AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val threshold =
                appBarLayout.totalScrollRange - requireContext().resources.displayMetrics.density * 56f
            val progress = min(1f, abs(verticalOffset) / threshold)
            newsRecyclerView.alpha = 1 - progress
        }
}