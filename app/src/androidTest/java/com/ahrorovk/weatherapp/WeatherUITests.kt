package com.ahrorovk.weatherapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ahrorovk.weatherapp.app.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 * I added some UI tests
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class WeatherUITests {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    //Test of Changing location
    @Test
    fun clicker_of_location_changer(): Unit = with(composeTestRule) {
        common()
        onNodeWithText("Current Location")
            .performClick()
        onNodeWithText("Khujand")
            .performClick()
    }

    //Test of Changing temp unit
    @Test
    fun clicker_of_temp_changer(): Unit = with(composeTestRule) {
        common()
        onNodeWithText("celsius(°C)")
            .assertIsDisplayed()
            .performClick()
        onNodeWithText("fahrenheit(°F)")
            .assertIsDisplayed()
            .performClick()
    }

    // Common request during tests
    private fun common(): Unit = with(composeTestRule) {
        onRoot().printToLog("Ui test")
        waitUntil(timeoutMillis = 5000) {
            onAllNodesWithTag("WeatherItem")
                .fetchSemanticsNodes().isNotEmpty()
        }
    }
}