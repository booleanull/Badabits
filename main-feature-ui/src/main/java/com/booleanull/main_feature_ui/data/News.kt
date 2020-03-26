package com.booleanull.main_feature_ui.data

import com.booleanull.core_ui.data.Item

data class News(
    val id: Int,
    val title: String,
    val icon: Boolean,
    val image: Boolean
) : Item