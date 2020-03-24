package com.booleanull.main_feature_ui.component.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.booleanull.core_ui.component.BaseFragment
import com.booleanull.main_feature_ui.R
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
            navigateToTab(
                when (it.itemId) {
                    R.id.action_list -> ListHostFragment()::class.java.simpleName
                    R.id.action_graph -> GraphHostFragment()::class.java.simpleName
                    else -> AchievementHostFragment()::class.java.simpleName
                }
            )
            return@setOnNavigationItemSelectedListener true
        }

        if(savedInstanceState == null) {
            navigationView.selectedItemId = R.id.action_list
        }
    }

    private fun navigateToTab(tab: String) {
        var currentFragment: Fragment? = null
        for (f in childFragmentManager.fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        val newFragment = childFragmentManager.findFragmentByTag(tab)
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return
        childFragmentManager.beginTransaction().apply {
            if (newFragment == null) {
                add(R.id.navigationContainer, getFragmentByTab(tab), tab)
            }
            if (currentFragment != null) {
                hide(currentFragment)
            }
            if (newFragment != null) {
                show(newFragment)
            }

            commitNow()
        }
    }

    private fun getFragmentByTab(tab: String): Fragment {
        return when (tab) {
            ListHostFragment::class.java.simpleName -> ListHostFragment()
            GraphHostFragment::class.java.simpleName -> GraphHostFragment()
            else -> AchievementHostFragment()
        }
    }
}