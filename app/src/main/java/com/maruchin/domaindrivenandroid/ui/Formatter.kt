package com.maruchin.domaindrivenandroid.ui

import com.maruchin.domaindrivenandroid.data.values.Points
import kotlin.time.Duration
import kotlin.time.DurationUnit

fun Points.format(): String {
    return "$value pts"
}

fun Duration.formatSeconds(): String {
    return toString(DurationUnit.SECONDS)
}
