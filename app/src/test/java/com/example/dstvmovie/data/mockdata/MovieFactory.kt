package com.example.dstvmovie.data.mockdata

import com.example.dstvmovie.data.model.Item
import com.example.dstvmovie.data.remote.response.BaseListResponse
import com.example.dstvmovie.data.remote.response.Component

fun createBaseListResponse(): BaseListResponse {

    val movie1 = Item(id = 1L, "", "", "", 4, "", "", "", 0)
    val movie2 = Item(id = 2L, "", "", "", 5, "", "", "", 0)
    val movie3 = Item(id = 3L, "", "", "", 8, "", "", "", 0)
    val movie4 = Item(id = 4L, "", "", "", 2, "", "", "", 0)
    val items = arrayListOf(movie1, movie2, movie3, movie4)

    return BaseListResponse("", components = arrayListOf(Component("", items)))
}