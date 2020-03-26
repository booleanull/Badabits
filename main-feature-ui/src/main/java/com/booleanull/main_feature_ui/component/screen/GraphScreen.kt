package com.booleanull.main_feature_ui.component.screen

import androidx.fragment.app.Fragment
import com.booleanull.core_ui.component.BaseScreen
import com.booleanull.main_feature_ui.component.fragment.GraphFragment

class GraphScreen : BaseScreen() {

    override fun getFragment(): Fragment {
        return GraphFragment()
    }
}