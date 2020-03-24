package com.booleanull.main_feature.ui.screen

import androidx.fragment.app.Fragment
import com.booleanull.core_ui.ui.BaseScreen
import com.booleanull.main_feature.ui.fragment.FirstSecondFragment

class FirstSecondScreen : BaseScreen() {

    override fun getFragment(): Fragment {
        return FirstSecondFragment()
    }
}