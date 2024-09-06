package com.devkishan.nseapi.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllIndices(
    val advances: String,
    val chart30dPath: String,
    val chart365dPath: String,
    val chartTodayPath: String,
    val date30dAgo: String,
    val date365dAgo: String,
    val declines: String,
    val dy: String,
    val high: Double,
    val index: String,
    val indexSymbol: String,
    val indicativeClose: Int,
    val key: String,
    val last: Double,
    val low: Double,
    val oneMonthAgo: Double,
    val oneWeekAgo: Double,
    val oneYearAgo: Double,
    val `open`: Double,
    val pb: String,
    val pe: String,
    val perChange30d: Double,
    val perChange365d: Double,
    val percentChange: Double,
    val previousClose: Double,
    val previousDay: Double,
    val unchanged: String,
    val variation: Double,
    val yearHigh: Double,
    val yearLow: Double
)