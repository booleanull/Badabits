package com.booleanull.main_feature.ui

import androidx.fragment.app.Fragment
import com.booleanull.core_ui.ui.BaseScreen

class NavigationScreen : BaseScreen() {

    override fun getFragment(): Fragment {
        return NavigationFragment()
    }
}