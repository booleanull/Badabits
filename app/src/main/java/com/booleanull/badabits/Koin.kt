package com.booleanull.badabits

import org.koin.dsl.module
import ru.terrakok.cicerone.BaseRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

val mainModule = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().navigatorHolder }
}