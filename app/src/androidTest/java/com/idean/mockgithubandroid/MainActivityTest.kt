import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.idean.mockgithubandroid.MainActivity
import com.idean.mockgithubandroid.R
import com.jakewharton.espresso.OkHttp3IdlingResource
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
    fun testFetchRudy() {
        onView(withId(R.id.searchText)).perform(typeText("EarlMegget21"))
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.name)).check(matches(withText("Rudy Sonetti")))
    }
}