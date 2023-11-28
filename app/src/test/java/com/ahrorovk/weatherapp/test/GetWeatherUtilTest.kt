package com.ahrorovk.weatherapp.test

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GetWeatherUtilTest {

    @Test
    fun `tempUnit is empty`() {
        val result = GetWeatherUtil.validateFields(
            0.0,
            0.0,
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `tempUnit is incorrect`() {
        val result = GetWeatherUtil.validateFields(
            0.0,
            0.0,
            "°F"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `tempUnit is correct, longitude and latitude are true`() {
        val result = GetWeatherUtil.validateFields(
            0.0,
            0.0,
            "fahrenheit"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `longitude is more than 180 return false`() {
        val result = GetWeatherUtil.validateFields(
            190.0,
            0.0,
            "fahrenheit"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `longitude is less than -180 return false`() {
        val result = GetWeatherUtil.validateFields(
            -190.0,
            0.0,
            "fahrenheit"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `latitude is more than 90 return false`() {
        val result = GetWeatherUtil.validateFields(
            0.0,
            100.0,
            "fahrenheit"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `latitude is less than -90 return false`() {
        val result = GetWeatherUtil.validateFields(
            0.0,
            -100.0,
            "fahrenheit"
        )
        assertThat(result).isFalse()
    }
}