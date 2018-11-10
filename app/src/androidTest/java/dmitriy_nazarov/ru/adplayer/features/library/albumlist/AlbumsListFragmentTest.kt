package dmitriy_nazarov.ru.adplayer.features.library.albumlist

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.view.View
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.core.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.core.ui.activities.TestActivity
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.utils.*
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumsListFragmentTest : FragmentTest<AlbumsListFragment>() {

    private lateinit var albumsListFragment: AlbumsListFragment

    @Before
    override fun setup() {
        activityTestRule.launchActivity(Intent())
        activityTestRule.activity.launchFragment(AlbumsListFragment())
        Espresso.onView(isRoot()).perform(TestUtils.waitFor(TestActivity.FRAGMENT_LAUNCH_WAIT_TIME_MILLISECOND))
        albumsListFragment = findFragment(AlbumsListFragment.TAG)
        albumsListFragment.albumListRepository.insertAlbums(TestDataManager.albumsList)
    }

    @Test
    fun getViewModel() {
        assertNotNull(albumsListFragment.albumListRepository)
    }

    @Test
    fun getAlbumListRepository() {
        assertNotNull(albumsListFragment.viewModel)
    }

    @Test
    fun checkFragmentAreShowingAllTracks() {
        RecyclerViewInteraction.onRecyclerView<Album>(withId(R.id.recycler_view)).
                withItems(albumsListFragment.albumListRepository.getAllAlbumsRaw()!!)
                .check(object : RecyclerViewInteraction.ItemViewAssertion<Album>{
                    override fun check(item: Album, view: View, exception: NoMatchingViewException?) {
                        matches(hasDescendant(withText(item.albumName))).check(view, exception)
                    }
                })
    }

}