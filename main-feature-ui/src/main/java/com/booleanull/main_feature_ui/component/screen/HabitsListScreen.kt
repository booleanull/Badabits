package com.booleanull.main_feature_ui.component.screen

import androidx.fragment.app.Fragment
import com.booleanull.core_ui.component.BaseScreen
import com.booleanull.main_feature_ui.component.fragment.HabitsListFragment

class HabitsListScreen : BaseScreen() {

    override fun getFragment(): Fragment {
        return HabitsListFragment()
    }
}