package com.booleanull.core_ui.component

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class LocalNavigationHolder {
    private val containers = HashMap<String, Cicerone<Router>>()

    fun getCicerone(tag: String): Cicerone<Router> {
        if (!containers.containsKey(tag)) {
            containers[tag] = Cicerone.create()
        }
        return containers[tag]!!
    }
}