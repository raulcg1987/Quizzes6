package uoc.quizz

import android.os.Build
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@LargeTest
class EspressoTest {

    @get:Rule  var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun intentsInit() {
        // initialize Espresso Intents capturing
        Intents.init()
    }

    @After
    fun intentsTeardown() {
        // release Espresso Intents capturing
        Intents.release()
    }

    @Test
    open fun changeText_sameActivity(): Unit {
        // Press the button.
        Espresso.onView(withId(R.id.send)).perform(ViewActions.click())

        // Press the second option.
        Espresso.onView(withId(R.id.radioButton2))
            .perform(ViewActions.click())

    }

}