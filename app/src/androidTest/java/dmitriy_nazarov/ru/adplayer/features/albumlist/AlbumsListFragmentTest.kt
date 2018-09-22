package dmitriy_nazarov.ru.adplayer.features.albumlist

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.view.View
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.RecyclerViewInteraction
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.ui.MainActivity
import dmitriy_nazarov.ru.adplayer.utils.DatabaseTestFillHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumsListFragmentTest {

    private val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setup() {
        DatabaseTestFillHelper.fill(InstrumentationRegistry.getTargetContext())
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun checkFragmentAreShowingAllTracks() {
        RecyclerViewInteraction.onRecyclerView<Album>(withId(R.id.recycler_view)).
                withItems(AlbumListRepository.getAllAlbumsRaw()!!)
                .check(object : RecyclerViewInteraction.ItemViewAssertion<Album>{
                    override fun check(item: Album, view: View, exception: NoMatchingViewException?) {
                        matches(hasDescendant(withText(item.albumName))).check(view, exception)
                    }
                })

    }

}