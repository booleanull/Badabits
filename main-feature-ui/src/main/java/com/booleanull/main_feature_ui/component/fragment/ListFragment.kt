package com.booleanull.main_feature_ui.component.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.booleanull.core_ui.component.BaseFragment
import com.booleanull.core_ui.component.LocalNavigationFragment
import com.booleanull.core_ui.component.LocalNavigationHolder
import com.booleanull.main_feature_ui.R
import com.booleanull.main_feature_ui.component.screen.HabitsListScreen
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class ListFragment : BaseFragment(), LocalNavigationFragment {

    private val localNavigationHolder: LocalNavigationHolder by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLocalRouter().replaceScreen(HabitsListScreen())
    }

    override fun getLocalRouter(): Router {
        return localNavigationHolder.getCicerone(ListFragment::class.java.simpleName)
            .router
    }

    override fun onResume() {
        super.onResume()
        localNavigationHolder.getCicerone(ListFragment::class.java.simpleName)
            .navigatorHolder.setNavigator(
            SupportAppNavigator(
                activity,
                childFragmentManager,
                R.id.container
            )
        )
    }

    override fun onPause() {
        localNavigationHolder.getCicerone(ListFragment::class.java.simpleName)
            .navigatorHolder
            .removeNavigator()
        super.onPause()
    }
}