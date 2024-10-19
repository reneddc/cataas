package com.calyr.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDto(
    @Json(name = "_id")
    val id: String,
    @Json(name = "mimetype")
    val mimeType: String,
    @Json(name = "size")
    val size: Int,
    @Json(name = "tags")
    val tags: List<String>
)
