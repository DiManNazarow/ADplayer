package dmitriy_nazarov.ru.adplayer

import android.content.Context
import dmitriy_nazarov.ru.adplayer.features.albumlist.AlbumListRepository
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication

import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
class RepositoriesTest {

    var context: Context? = null;

    @Before
    fun setUp() {
        context = ShadowApplication.getInstance().applicationContext
        AppDatabase.getInstance(context)
    }

    @Test
    fun testWriteAndRead() {
        AlbumListRepository.insertAlbums(TestDataManager.albumsList)
        TrackListRepository.insertTracks(TestDataManager.tracksList)

        assertEquals(AlbumListRepository.getCount(), TestDataManager.albumsList.size)
        assertEquals(TrackListRepository.getCount(), TestDataManager.tracksList.size)

        val albumsList = AlbumListRepository.getAllAlbumsRaw()

        assertNotNull(albumsList)
        assertEquals(albumsList.size, TestDataManager.albumsList.size)

        assertTrue(albumsList[0].equals(TestDataManager.albumsList[0]))
        assertTrue(albumsList[1].equals(TestDataManager.albumsList[1]))
        assertTrue(albumsList[2].equals(TestDataManager.albumsList[2]))

        assertTrue(albumsList[0].tracksList!![0].equals(TestDataManager.tracksList[0]))
        assertTrue(albumsList[0].tracksList!![1].equals(TestDataManager.tracksList[1]))
        assertTrue(albumsList[1].tracksList!![0].equals(TestDataManager.tracksList[2]))
        assertTrue(albumsList[1].tracksList!![1].equals(TestDataManager.tracksList[3]))
        assertTrue(albumsList[2].tracksList!![0].equals(TestDataManager.tracksList[4]))

    }



}