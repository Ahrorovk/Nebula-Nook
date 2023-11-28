package com.ahrorovk.weatherapp.test

object GetWeatherUtil {
    /*
    * Validating of fields
    * if ...
    * ...latitude>90 || latitude<-90
    * ...longitude>180 || longitude<-180
    * ...tempUnit is empty
    * ...tempUnit is incorrect
    * */
    private val tempUnits = listOf<String>("celsius", "fahrenheit")
    fun validateFields(
        long: Double,
        lat: Double,
        tempUnit: String
    ): Boolean {
        if (tempUnit.isEmpty() || tempUnit != tempUnits[0] || tempUnit != tempUnits[1]) return false
        if (long > 180 || long < -180) return false
        if (lat > 90 || lat < -90) return false
        return true
    }
}