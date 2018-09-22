package dmitriy_nazarov.ru.adplayer.features.tracklist

import android.support.test.runner.AndroidJUnit4
import android.support.test.rule.ActivityTestRule;
import dmitriy_nazarov.ru.adplayer.ui.MainActivity
import org.junit.runner.RunWith

import android.content.Intent
import android.support.test.InstrumentationRegistry

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.view.View
import dmitriy_nazarov.ru.adplayer.utils.DatabaseTestFillHelper
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.RecyclerViewInteraction
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track
import org.junit.Before
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class TrackListFragmentTest {

    private val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setup() {
        DatabaseTestFillHelper.fill(InstrumentationRegistry.getTargetContext())
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun checkFragmentAreShowingAllTracks() {
        RecyclerViewInteraction.onRecyclerView<Track>(withId(R.id.recycler_view)).
                withItems(TrackListRepository.getAllTracksRaw()!!)
                .check(object : RecyclerViewInteraction.ItemViewAssertion<Track>{
                    override fun check(item: Track, view: View, exception: NoMatchingViewException?) {
                        matches(hasDescendant(withText(item.trackTitle))).check(view, exception)
                    }
                })

    }

}