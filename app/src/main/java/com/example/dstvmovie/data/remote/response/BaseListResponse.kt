package com.example.dstvmovie.data.remote.response

import com.example.dstvmovie.data.model.Item
import com.squareup.moshi.Json

open class BaseListResponse(
    @Json(name = "type") val type: String?,
    @Json(name = "components") val components: List<Component>?
) : BaseResponse()

data class Component(
    @Json(name = "type") val type: String?,
    @Json(name = "items") val items: List<Item>?,
)