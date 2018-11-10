package dmitriy_nazarov.ru.adplayer.features.library.tracklist

import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

import android.content.Intent
import android.support.test.espresso.Espresso

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.view.View
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.core.ui.activities.TestActivity
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.utils.*
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class TrackListFragmentTest : FragmentTest<TrackListFragment>() {

    private lateinit var trackListFragment: TrackListFragment

    @Before
    override fun setup() {
        activityTestRule.launchActivity(Intent())
        activityTestRule.activity.launchFragment(TrackListFragment())
        Espresso.onView(isRoot()).perform(TestUtils.waitFor(TestActivity.FRAGMENT_LAUNCH_WAIT_TIME_MILLISECOND))
        trackListFragment = findFragment(TrackListFragment.TAG)
        trackListFragment.trackListRepository.insertTracks(TestDataManager.tracksList)
    }

    @Test
    fun getViewModel() {
        assertNotNull(trackListFragment.trackListRepository)
    }

    @Test
    fun getAlbumListRepository() {
        assertNotNull(trackListFragment.viewModel)
    }

    @Test
    fun checkFragmentAreShowingAllTracks() {
        RecyclerViewInteraction.onRecyclerView<Track>(withId(R.id.recycler_view)).
                withItems(trackListFragment.trackListRepository.getAllTracksRaw()!!)
                .check(object : RecyclerViewInteraction.ItemViewAssertion<Track>{
                    override fun check(item: Track, view: View, exception: NoMatchingViewException?) {
                        matches(hasDescendant(withText(item.trackTitle))).check(view, exception)
                    }
                })

    }

}