package com.booleanull.badabits

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.booleanull.core_ui.ui.BaseFragment
import com.booleanull.main_feature.ui.NavigationScreen
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainFragment: BaseFragment() {

    private val navigatorHolder: NavigatorHolder by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO: Think about loading
        return FrameLayout(requireContext()).apply {
            id = R.id.mainContainer
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            addView(ProgressBar(requireContext()).apply {
                id = R.id.mainProgress
                layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router.replaceScreen(NavigationScreen())
        view.findViewById<ProgressBar>(R.id.mainProgress).isVisible = false
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(SupportAppNavigator(requireActivity(), childFragmentManager, R.id.mainContainer))
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}