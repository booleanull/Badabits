package com.booleanull.badabits

import com.booleanull.core_ui.component.LocalNavigationHolder
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

val mainModule = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().navigatorHolder }
    single { LocalNavigationHolder() }
}