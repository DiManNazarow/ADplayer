package dmitriy_nazarov.ru.adplayer

import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.db.LibraryTransform
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.TrackEntity
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import org.junit.Assert.*
import org.junit.Test
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class DataTransformationTests {

    var context: Context? = null;
    var contentResolver: ContentResolver?= null

    @Before
    fun setUp() {
        context = ShadowApplication.getInstance().applicationContext
        contentResolver = ShadowApplication.getInstance().applicationContext.contentResolver
        contentResolver = ShadowApplication.getInstance().applicationContext.contentResolver
        writeAlbums()
        writeTracks()
    }

    @Test
    fun areAlbumsContentFullyExisting() {
        val cursor = contentResolver!!.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null)
        assertNotNull(cursor)
        assertEquals(cursor!!.count, 3)
    }

    @Test
    fun areTracksContentFullyExisting() {
        val cursor = contentResolver!!.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        assertNotNull(cursor)
        assertEquals(cursor!!.count, 5)
    }

    @Test
    fun areAlbumsTransformingSuccessfully() {
        val cursor = contentResolver!!.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null)
        val list: List<Album>? = LibraryTransform.transformDeviceAlbumLibIntoAppLib(cursor!!)
        assertNotNull(list)
        assertEquals(list!!.size, 3)
        assertTrue(list[0].equals(TestDataManager.albumsList[0]))
        assertTrue(list[1].equals(TestDataManager.albumsList[1]))
        assertTrue(list[2].equals(TestDataManager.albumsList[2]))
    }

    @Test
    fun areTracksTransformingSuccessfully() {
        val cursor = contentResolver!!.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        val list: List<TrackEntity>? = LibraryTransform.transformDeviceTrackLibIntoAppLib(cursor!!)
        assertNotNull(list)
        assertEquals(list!!.size, 5)
        assertTrue(list[0].equals(TestDataManager.tracksList[0]))
        assertTrue(list[1].equals(TestDataManager.tracksList[1]))
        assertTrue(list[2].equals(TestDataManager.tracksList[2]))
        assertTrue(list[3].equals(TestDataManager.tracksList[3]))
        assertTrue(list[4].equals(TestDataManager.tracksList[4]))
    }

//    @Test
//    fun areAlbumsWritingToAppBDSuccessfully() {
//        val cursor = contentResolver!!.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null)
//        val list: List<Album>? = LibraryTransform.transformDeviceAlbumLibIntoAppLib(cursor!!)
//        assertNotNull(list)
//        assertEquals(list!!.size, 3)
//        for (album: Album in list) {
//            albumListDao!!.insertAlbum(album)
//        }
//        assertEquals(albumListDao!!.getCount(), 3)
//    }

//    @Test
//    fun areTracksWritingToAppBDSuccessfully() {
//        val cursor = contentResolver!!.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
//        val list: List<TrackEntity>? = LibraryTransform.transformDeviceTrackLibIntoAppLib(cursor!!)
//        assertNotNull(list)
//        assertEquals(list!!.size, 5)
//        for (track: TrackEntity in list) {
//            trackListDao!!.insertTrack(track)
//        }
//        assertEquals(trackListDao!!.getCount(), 5)
//    }

//    @Test
//    fun areAlbumsReadingFromAppDBSuccessfully() {
//
//        val cursorTrack = contentResolver!!.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
//        val listTracks: List<TrackEntity>? = LibraryTransform.transformDeviceTrackLibIntoAppLib(cursorTrack!!)
//        assertNotNull(listTracks)
//        for (track: TrackEntity in listTracks!!) {
//            trackListDao!!.insertTrack(track)
//        }
//
//        assertTrue(listTracks[0].equals(trackList[0]))
//        assertTrue(listTracks[1].equals(trackList[1]))
//        assertTrue(listTracks[2].equals(trackList[2]))
//        assertTrue(listTracks[3].equals(trackList[3]))
//        assertTrue(listTracks[4].equals(trackList[4]))
//
//        val cursor = contentResolver!!.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null)
//        var list: List<Album>? = LibraryTransform.transformDeviceAlbumLibIntoAppLib(cursor!!)
//        assertNotNull(list)
//        assertEquals(list!!.size, 3)
//        for (album: Album in list) {
//            albumListDao!!.insertAlbum(album)
//        }
//        assertEquals(albumListDao!!.getCount(), 3)
//        list = albumListDao!!.getAllAlbumsRaw();
//        assertNotNull(list)
//        assertEquals(list.size, 3)
//
//        assertTrue(list[0].equals(albumsList[0]))
//        assertTrue(list[1].equals(albumsList[1]))
//        assertTrue(list[2].equals(albumsList[2]))
//
//        assertTrue(list[0].tracksList!![0].equals(trackList[0]))
//        assertTrue(list[0].tracksList!![1].equals(trackList[1]))
//        assertTrue(list[1].tracksList!![0].equals(trackList[2]))
//        assertTrue(list[1].tracksList!![1].equals(trackList[3]))
//        assertTrue(list[2].tracksList!![0].equals(trackList[4]))
//
//    }
//
//    @Test
//    fun areTracksReadingFromAppDBSuccessfully() {
//        val cursor = contentResolver!!.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
//        var list: List<TrackEntity>? = LibraryTransform.transformDeviceTrackLibIntoAppLib(cursor!!)
//        assertNotNull(list)
//        assertEquals(list!!.size, 5)
//        for (track: TrackEntity in list) {
//            trackListDao!!.insertTrack(track)
//        }
//        list = trackListDao!!.getAllTracksRaw()
//        assertNotNull(list)
//        assertEquals(list.size, 5)
//        assertTrue(list[0].equals(trackList[0]))
//        assertTrue(list[1].equals(trackList[1]))
//        assertTrue(list[2].equals(trackList[2]))
//        assertTrue(list[3].equals(trackList[3]))
//        assertTrue(list[4].equals(trackList[4]))
//    }


    private fun writeAlbums() {
        shadowOf(contentResolver).setCursor(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, TestCursorHelper.getCursorWithAlbums())
    }

    private fun writeTracks() {
        shadowOf(contentResolver).setCursor(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, TestCursorHelper.getCursorWithTracks())
    }

}