package com.booleanull.main_feature.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.booleanull.core_ui.ui.BaseFragment
import com.booleanull.main_feature.R
import kotlinx.android.synthetic.main.fragment_navigation.*

class NavigationFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationView.setOnNavigationItemSelectedListener {
            childFragmentManager
                .beginTransaction()
                .replace(
                    R.id.navigationContainer, when (it.itemId) {
                        R.id.action_map -> FirstFragment()
                        R.id.action_dial -> SecondFragment()
                        else -> ThirdFragment()
                    }
                )
                .commit()
            return@setOnNavigationItemSelectedListener true
        }
        if (savedInstanceState == null) {
            navigationView.selectedItemId = R.id.action_map
        }
    }
}