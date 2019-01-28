import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.idean.mockgithubandroid.MainActivity
import com.idean.mockgithubandroid.R
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.squareup.spoon.Spoon
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    lateinit var resource: IdlingResource

    @Before
    fun setUp() {
        resource = OkHttp3IdlingResource.create("OkHttp", MainActivity.okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(resource)
    }

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testInitialScreen() {
        Spoon.screenshot(activityRule.activity, "startInitial")
        onView(withText("Rechercher")).check(matches(isDisplayed()))
        Spoon.screenshot(activityRule.activity, "endInitial")
    }

    @Test
    fun testFetchRudy() {
        Spoon.screenshot(activityRule.activity, "startRudy")
        onView(withId(R.id.searchText)).perform(typeText("EarlMegget21"))
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.name)).check(matches(withText("Rudy Sonetti")))
        Spoon.screenshot(activityRule.activity, "endRudy")
    }

    @Test
    fun testFetchFlorent() {
        Spoon.screenshot(activityRule.activity, "startFlorent")
        onView(withId(R.id.searchText)).perform(typeText("florent37"))
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.name)).check(matches(withText("Florent Champigny")))
        Spoon.screenshot(activityRule.activity, "endFlorent")
    }
}