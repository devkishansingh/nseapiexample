package com.devkishan.nseapi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dates(
    val oneMonthAgo: String,
    val oneWeekAgo: String,
    val oneYearAgo: String,
    val previousDay: String
)