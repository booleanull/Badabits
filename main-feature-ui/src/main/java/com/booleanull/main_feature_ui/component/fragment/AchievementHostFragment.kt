package com.booleanull.main_feature_ui.component.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.booleanull.core_ui.component.BaseFragment
import com.booleanull.core_ui.component.LocalNavigationFragment
import com.booleanull.core_ui.component.LocalNavigationHolder
import com.booleanull.main_feature_ui.R
import com.booleanull.main_feature_ui.component.screen.AchievementScreen
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class AchievementHostFragment : BaseFragment(), LocalNavigationFragment {

    private val localNavigationHolder: LocalNavigationHolder by inject()

    private val navigator by lazy {
        SupportAppNavigator(activity, childFragmentManager, R.id.container)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_achievement_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            getLocalRouter().replaceScreen(AchievementScreen())
        }
    }

    override fun getLocalRouter(): Router {
        return localNavigationHolder.getCicerone(AchievementHostFragment::class.java.simpleName)
            .router
    }

    override fun onResume() {
        super.onResume()
        localNavigationHolder.getCicerone(AchievementHostFragment::class.java.simpleName)
            .navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        localNavigationHolder.getCicerone(AchievementHostFragment::class.java.simpleName)
            .navigatorHolder
            .removeNavigator()
        super.onPause()
    }
}