package com.booleanull.main_feature.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.booleanull.core_ui.ui.BaseFragment
import com.booleanull.core_ui.ui.LocalNavigationFragment
import com.booleanull.core_ui.ui.LocalNavigationHolder
import com.booleanull.main_feature.R
import com.booleanull.main_feature.ui.screen.FirstSecondScreen
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class FirstFragment : BaseFragment(), LocalNavigationFragment {

    private val localNavigationHolder: LocalNavigationHolder by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLocalRouter().replaceScreen(FirstSecondScreen())
    }

    override fun getLocalRouter(): Router {
        return localNavigationHolder.getCicerone(FirstFragment::class.java.simpleName)
            .router
    }

    override fun onResume() {
        super.onResume()
        localNavigationHolder.getCicerone(FirstFragment::class.java.simpleName)
            .navigatorHolder.setNavigator(
            SupportAppNavigator(
                activity,
                childFragmentManager,
                R.id.container
            )
        )
    }

    override fun onPause() {
        localNavigationHolder.getCicerone(FirstFragment::class.java.simpleName)
            .navigatorHolder
            .removeNavigator()
        super.onPause()
    }
}