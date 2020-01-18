package com.irsyaad.dicodingsubmission.seport.ui

import android.view.KeyEvent
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.irsyaad.dicodingsubmission.seport.R
import com.irsyaad.dicodingsubmission.seport.view.main.MainActivity
import org.junit.Rule
import org.junit.Test

class SearchTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun searchActionTest(){
        onView(withId(R.id.btnSearchMain)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSearchMain)).perform(click())

        onView(withContentDescription("Search")).check(matches(isDisplayed()))
        onView(withContentDescription("Search")).perform(click())

        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Barcelona"),
            pressKey(KeyEvent.KEYCODE_ENTER)
        )
        Thread.sleep(5000)

        onView(withId(R.id.rvSearch)).check(matches(isDisplayed()))
        Thread.sleep(1000)

    }
}