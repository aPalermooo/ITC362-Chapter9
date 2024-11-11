package com.example.chapter_nine

import android.view.KeyEvent
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CrimeDetailFragmentTest {

    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    @Before
    fun setUp () {
        scenario = launchFragmentInContainer<CrimeDetailFragment>()
    }

    @After
    fun cleanUp () {
        scenario.close()
    }

    @Test
    fun fragmentActivation () {
        //Ensure fragment properly activates in container
        scenario.onFragment{ fragment -> assertNotNull(fragment.view) }
    }

    @Test
    fun crimeIsAccessible () {
        //Ensure that crime is a public attribute that can be accessed by test
        val fragmentCrime = scenario.onFragment{ _ -> crime}
        assertNotNull(fragmentCrime)
    }

    @Test
    fun editCrimeTitleFunctionality () {
        val expectedPostValue = "TEST"
        val textBox = onView(withId(R.id.crime_title))
        textBox.perform(typeText(expectedPostValue))
        textBox.perform(pressKey(KeyEvent.KEYCODE_ENTER))
        assertEquals(expectedPostValue, crime.title)
    }

    @Test
    fun crimeSolvedFunctionality () {
        assertFalse(crime.isSolved)

        onView(withId(R.id.crime_solved)).perform(click())
        assertTrue(crime.isSolved)

        onView(withId(R.id.crime_solved)).perform(click())
        assertFalse(crime.isSolved)
    }
}

