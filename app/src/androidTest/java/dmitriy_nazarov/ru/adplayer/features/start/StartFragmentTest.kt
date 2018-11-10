package dmitriy_nazarov.ru.adplayer.features.start

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.widget.ProgressBar
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.core.ui.activities.TestActivity
import dmitriy_nazarov.ru.adplayer.utils.FragmentTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartFragmentTest : FragmentTest<StartFragment>() {

    private lateinit var startFragment: StartFragment

    @Before
    override fun setup() {
        activityTestRule.launchActivity(Intent())
        activityTestRule.activity.launchFragment(StartFragment())
        waitTime(TestActivity.FRAGMENT_LAUNCH_WAIT_TIME_MILLISECOND)
        startFragment = findFragment(StartFragment.TAG)
    }

    @Test
    fun testAppTitle() {
        onView(withId(R.id.app_name_text_view)).check(matches(withText(R.string.app_name)))
    }

    @Test
    fun testActionTitle() {
        onView(withId(R.id.action_text_view)).check(matches(withText(R.string.prepare_library)))
    }

    @Test
    fun testProgressBar() {
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        startFragment.updateProgress(50)
        assertEquals(startFragment.view?.findViewById<ProgressBar>(R.id.progress_bar)?.progress, 50)
        startFragment.updateProgress(99)
        assertEquals(startFragment.view?.findViewById<ProgressBar>(R.id.progress_bar)?.progress, 99)
    }

    @Test
    fun getTrackListRepository() {
        assertNotNull(startFragment.trackListRepository)
    }

    @Test
    fun getAlbumListRepository() {
        assertNotNull(startFragment.albumListRepository)
    }
}