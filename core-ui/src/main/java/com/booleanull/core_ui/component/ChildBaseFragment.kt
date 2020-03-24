package com.booleanull.core_ui.component

import ru.terrakok.cicerone.Router

open class ChildBaseFragment: BaseFragment(), LocalNavigationFragment {

    override fun getLocalRouter(): Router {
        return (requireParentFragment() as LocalNavigationFragment).getLocalRouter()
    }
}