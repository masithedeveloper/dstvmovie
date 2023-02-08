package com.example.dstvmovie.data.constant

import com.example.dstvmovie.compose.ui.home.BottomNavItem

object Constants {
    const val DEFAULT_FIRST_PAGE = 1
    const val DEFAULT_NUM_VISIBLE_THRESHOLD = 5
    const val DEFAULT_ITEM_PER_PAGE = 20
    const val DATABASE_NAME = "DSTV_MOVIES_DB"

    val BottomNavItems = listOf(
        BottomNavItem(
            valueToOrderBy = "rank",
            label = "Rank"
        ),
        BottomNavItem(
            valueToOrderBy = "releaseDate",
            label = "Release Date"
        )
    )
}