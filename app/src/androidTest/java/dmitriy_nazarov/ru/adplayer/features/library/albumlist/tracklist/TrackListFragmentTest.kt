package dmitriy_nazarov.ru.adplayer.features.library.albumlist.tracklist

import android.support.test.runner.AndroidJUnit4
import android.support.test.rule.ActivityTestRule;
import dmitriy_nazarov.ru.adplayer.ui.activities.main.MainActivity
import org.junit.runner.RunWith

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.view.View
import dmitriy_nazarov.ru.adplayer.utils.DatabaseTestFillHelper
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.RecyclerViewInteraction
import dmitriy_nazarov.ru.adplayer.TestUtils
import dmitriy_nazarov.ru.adplayer.features.core.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.TrackListRepository
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.utils.TestDataManager
import org.junit.Before
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class TrackListFragmentTest {

    private val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var trackListRepository: TrackListRepository

    @Before
    fun setup() {
        DatabaseTestFillHelper.fill()
        activityTestRule.launchActivity(Intent())
        trackListRepository = TrackListRepository(AppDatabase.getInstance(InstrumentationRegistry.getTargetContext())!!)
        trackListRepository.insertTracks(TestDataManager.tracksList)
    }

    @Test
    fun checkFragmentAreShowingAllTracks() {
        onView(isRoot()).perform(TestUtils.waitFor(2000))
        RecyclerViewInteraction.onRecyclerView<Track>(withId(R.id.recycler_view)).
                withItems(trackListRepository.getAllTracksRaw()!!)
                .check(object : RecyclerViewInteraction.ItemViewAssertion<Track>{
                    override fun check(item: Track, view: View, exception: NoMatchingViewException?) {
                        matches(hasDescendant(withText(item.trackTitle))).check(view, exception)
                    }
                })

    }

}