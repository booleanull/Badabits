package com.booleanull.main_feature_ui.component.fragment

import android.animation.ArgbEvaluator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.booleanull.core_ui.component.BaseFragment
import com.booleanull.main_feature_ui.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_list.*
import kotlin.math.min

class ListFragment : BaseFragment() {

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
    }

    private val appBarLayoutChanged =
        AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val argbEvaluator = ArgbEvaluator()
            val threshold =
                appBarLayout.totalScrollRange - requireContext().resources.displayMetrics.density * 56f
            val progress = min(1f, kotlin.math.abs(verticalOffset) / threshold)
            tvTitle.setTextColor(
                argbEvaluator.evaluate(
                    progress,
                    Color.parseColor("#FFFFFF"),
                    Color.parseColor("#000000")
                ) as Int
            )
            collapsing.setContentScrimColor(
                argbEvaluator.evaluate(
                    progress,
                    ContextCompat.getColor(requireContext(), R.color.design_default_color_primary),
                    Color.parseColor("#FFFFFF")
                ) as Int
            )
        }
}