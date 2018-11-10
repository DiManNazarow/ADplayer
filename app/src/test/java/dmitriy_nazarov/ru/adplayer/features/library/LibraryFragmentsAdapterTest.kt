package dmitriy_nazarov.ru.adplayer.features.library

import android.support.v4.app.FragmentActivity
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumsListFragment
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.TrackListFragment
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LibraryFragmentsAdapterTest {

    private lateinit var fragmentActivity: FragmentActivity

    private lateinit var libraryFragmentsAdapter: LibraryFragmentsAdapter

    @Before
    fun setUp() {
        fragmentActivity = Robolectric.buildActivity(FragmentActivity::class.java).create().start().resume().get()
        libraryFragmentsAdapter = LibraryFragmentsAdapter(fragmentActivity, fragmentActivity.supportFragmentManager)
    }

    @Test
    fun getItem() {
        assertNotNull(libraryFragmentsAdapter.getItem(0))
        assertEquals(libraryFragmentsAdapter.getItem(0)::class, TrackListFragment::class)
        assertNotNull(libraryFragmentsAdapter.getItem(1))
        assertEquals(libraryFragmentsAdapter.getItem(1)::class, AlbumsListFragment::class)
    }

    @Test
    fun getCount() {
        assertEquals(libraryFragmentsAdapter.count, LibraryFragmentsAdapter.FRAGMENT_COUNT)
    }

    @Test
    fun getPageTitle() {
        assertNotNull(libraryFragmentsAdapter.getPageTitle(0))
        assertEquals(libraryFragmentsAdapter.getPageTitle(0), fragmentActivity.getString(R.string.tracks))
        assertNotNull(libraryFragmentsAdapter.getPageTitle(1))
        assertEquals(libraryFragmentsAdapter.getPageTitle(1), fragmentActivity.getString(R.string.albums))
    }
}