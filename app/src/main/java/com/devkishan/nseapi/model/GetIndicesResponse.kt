package com.devkishan.nseapi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetIndicesResponse(
    val advances: Int,
    val `data`: List<AllIndices>,
    val date30dAgo: String,
    val date365dAgo: String,
    val dates: Dates,
    val declines: Int,
    val timestamp: String,
    val unchanged: Int
)