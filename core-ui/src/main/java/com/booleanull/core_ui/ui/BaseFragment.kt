package com.booleanull.core_ui.ui

import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Router

open class BaseFragment: Fragment() {

    private val router: Router by inject()
}