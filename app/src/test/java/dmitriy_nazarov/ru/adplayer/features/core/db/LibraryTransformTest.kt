package dmitriy_nazarov.ru.adplayer.features.core.db

import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore
import dmitriy_nazarov.ru.adplayer.TestCursorHelper
import dmitriy_nazarov.ru.adplayer.TestDataManager
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.AlbumEntity
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.TrackEntity
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import org.junit.Assert.*
import org.junit.Test
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class LibraryTransformTest {

    lateinit var context: Context
    lateinit var contentResolver: ContentResolver

    @Before
    fun setUp() {
        context = ShadowApplication.getInstance().applicationContext
        contentResolver = ShadowApplication.getInstance().applicationContext.contentResolver
        writeAlbums()
        writeTracks()
    }

    @Test
    fun areAlbumsContentFullyExisting() {
        val cursor = MediaStoreAccessHelper.getAllAlbums(context)
        assertNotNull(cursor)
        assertEquals(cursor!!.count, 3)
    }

    @Test
    fun areTracksContentFullyExisting() {
        val cursor = MediaStoreAccessHelper.getAllTracks(context)
        assertNotNull(cursor)
        assertEquals(cursor!!.count, 5)
    }

    @Test
    fun areAlbumsTransformingSuccessfully() {
        val cursor = MediaStoreAccessHelper.getAllAlbums(context)
        val list: List<AlbumEntity>? = LibraryTransform.transformDeviceAlbumLibIntoAppLib(cursor!!)
        assertNotNull(list)
        assertEquals(list!!.size, 3)
        assertTrue(list[0].equals(TestDataManager.albumsList[0]))
        assertTrue(list[1].equals(TestDataManager.albumsList[1]))
        assertTrue(list[2].equals(TestDataManager.albumsList[2]))
    }

    @Test
    fun areTracksTransformingSuccessfully() {
        val cursor = MediaStoreAccessHelper.getAllTracks(context)
        val list: List<TrackEntity>? = LibraryTransform.transformDeviceTrackLibIntoAppLib(cursor!!)
        assertNotNull(list)
        assertEquals(list!!.size, 5)
        assertTrue(list[0].equals(TestDataManager.tracksList[0]))
        assertTrue(list[1].equals(TestDataManager.tracksList[1]))
        assertTrue(list[2].equals(TestDataManager.tracksList[2]))
        assertTrue(list[3].equals(TestDataManager.tracksList[3]))
        assertTrue(list[4].equals(TestDataManager.tracksList[4]))
    }

    private fun writeAlbums() {
        shadowOf(contentResolver).setCursor(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, TestCursorHelper.getCursorWithAlbums())
    }

    private fun writeTracks() {
        shadowOf(contentResolver).setCursor(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, TestCursorHelper.getCursorWithTracks())
    }

}