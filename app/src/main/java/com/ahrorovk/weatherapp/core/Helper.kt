package com.ahrorovk.weatherapp.core

import com.ahrorovk.weatherapp.core.models.Degree

fun getDegrees() = listOf<Degree>(
    Degree(
        "celsius",
        "°C",
        0
    ),
    Degree(
        "fahrenheit",
        "°F",
        1
    )
)

enum class Location {
    Khujand {
        override val lat: Double = 40.289306
        override val long: Double = 69.6232171
    },
    Dushanbe {
        override val lat: Double = 38.5425835
        override val long: Double = 68.81521423589763
    },
    Current_Location {
        override val long: Double = 0.0
        override val lat: Double = 0.0
    };
    abstract val long: Double
    abstract val lat: Double
}

fun getLocations() = listOf<Location>(
    Location.Current_Location,
    Location.Khujand,
    Location.Dushanbe
)
fun String.formatLocation(): String {
    return this.replace("_", " ")
}