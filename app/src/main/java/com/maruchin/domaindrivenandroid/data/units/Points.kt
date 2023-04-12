package com.maruchin.domaindrivenandroid.data.units

@JvmInline
value class Points(val value: Int) : Comparable<Points> {
    init {
        check(value >= 0)
    }

    override fun compareTo(other: Points): Int {
        return value.compareTo(other.value)
    }

    operator fun plus(other: Points): Points {
        return Points(value + other.value)
    }

    operator fun minus(other: Points): Points {
        return Points(value - other.value)
    }
}
