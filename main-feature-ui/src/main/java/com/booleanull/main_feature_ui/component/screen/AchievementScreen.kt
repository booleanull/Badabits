package com.booleanull.main_feature_ui.component.screen

import androidx.fragment.app.Fragment
import com.booleanull.core_ui.component.BaseScreen
import com.booleanull.main_feature_ui.component.fragment.AchievementFragment

class AchievementScreen : BaseScreen() {

    override fun getFragment(): Fragment {
        return AchievementFragment()
    }
}