package dmitriy_nazarov.ru.adplayer.features.library

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.core.ui.activities.TestActivity
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumsListFragment
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.TrackListFragment
import dmitriy_nazarov.ru.adplayer.utils.FragmentTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class LibraryFragmentTest : FragmentTest<LibraryFragment>() {

    private lateinit var libraryFragment: LibraryFragment

    @Before
    override fun setup() {
        activityTestRule.launchActivity(Intent())
        activityTestRule.activity.launchFragment(LibraryFragment())
        waitTime(TestActivity.FRAGMENT_LAUNCH_WAIT_TIME_MILLISECOND)
        libraryFragment = findFragment(LibraryFragment.TAG)
    }

    @Test
    fun testViewPager() {
        onView(withId(R.id.library_view_pager)).check(matches(isDisplayed()))
        assertNotNull(findFragment(libraryFragment.childFragmentManager, getViewPagerFragmentTag(0)))
        onView(withId(R.id.library_view_pager)).perform(swipeLeft())
        assertNotNull(findFragment(libraryFragment.childFragmentManager, getViewPagerFragmentTag(1)))
    }

    private fun getViewPagerFragmentTag(position: Int) : String {
        return "android:switcher:${R.id.library_view_pager}:$position"
    }

}