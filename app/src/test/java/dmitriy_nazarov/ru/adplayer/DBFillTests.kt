package dmitriy_nazarov.ru.adplayer

import android.arch.persistence.room.Room
import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore
import dmitriy_nazarov.ru.adplayer.features.albumlist.AlbumListDao
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.AlbumEntity
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.db.LibraryTransform
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListDao
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.TrackEntity
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import org.junit.Assert.*
import org.junit.Test
import org.robolectric.Shadows.shadowOf
import org.robolectric.fakes.RoboCursor
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class DBFillTests {

    var context: Context? = null;
    var contentResolver: ContentResolver?= null

    var appDatabase: AppDatabase? = null
    var trackListDao: TrackListDao? = null
    var albumListDao: AlbumListDao? = null

    var albumsList: ArrayList<AlbumEntity> = arrayListOf()
    var trackList: ArrayList<TrackEntity> = arrayListOf()

    @Before
    fun setUp() {
        context = ShadowApplication.getInstance().applicationContext
        contentResolver = ShadowApplication.getInstance().applicationContext.contentResolver
        appDatabase = Room.inMemoryDatabaseBuilder(context!!, AppDatabase::class.java).allowMainThreadQueries().build()
        trackListDao = appDatabase!!.trackListDao()
        albumListDao = appDatabase!!.albumListDao()
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
        assertTrue(list[0].equals(albumsList[0]))
        assertTrue(list[1].equals(albumsList[1]))
        assertTrue(list[2].equals(albumsList[2]))
    }

    @Test
    fun areTracksTransformingSuccessfully() {
        val cursor = contentResolver!!.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        val list: List<TrackEntity>? = LibraryTransform.transformDeviceTrackLibIntoAppLib(cursor!!)
        assertNotNull(list)
        assertEquals(list!!.size, 5)
        assertTrue(list[0].equals(trackList[0]))
        assertTrue(list[1].equals(trackList[1]))
        assertTrue(list[2].equals(trackList[2]))
        assertTrue(list[3].equals(trackList[3]))
        assertTrue(list[4].equals(trackList[4]))
    }

    @Test
    fun areAlbumsWritingToAppBDSuccessfully() {
        val cursor = contentResolver!!.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null)
        val list: List<Album>? = LibraryTransform.transformDeviceAlbumLibIntoAppLib(cursor!!)
        assertNotNull(list)
        assertEquals(list!!.size, 3)
        for (album: Album in list) {
            albumListDao!!.insertAlbum(album)
        }
        assertEquals(albumListDao!!.getCount(), 3)
    }

    @Test
    fun areTracksWritingToAppBDSuccessfully() {
        val cursor = contentResolver!!.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        val list: List<TrackEntity>? = LibraryTransform.transformDeviceTrackLibIntoAppLib(cursor!!)
        assertNotNull(list)
        assertEquals(list!!.size, 5)
        for (track: TrackEntity in list) {
            trackListDao!!.insertTrack(track)
        }
        assertEquals(trackListDao!!.getCount(), 5)
    }

    @Test
    fun areAlbumsReadingFromAppDBSuccessfully() {

        val cursorTrack = contentResolver!!.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        val listTracks: List<TrackEntity>? = LibraryTransform.transformDeviceTrackLibIntoAppLib(cursorTrack!!)
        assertNotNull(listTracks)
        for (track: TrackEntity in listTracks!!) {
            trackListDao!!.insertTrack(track)
        }

        assertTrue(listTracks[0].equals(trackList[0]))
        assertTrue(listTracks[1].equals(trackList[1]))
        assertTrue(listTracks[2].equals(trackList[2]))
        assertTrue(listTracks[3].equals(trackList[3]))
        assertTrue(listTracks[4].equals(trackList[4]))

        val cursor = contentResolver!!.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, null)
        var list: List<Album>? = LibraryTransform.transformDeviceAlbumLibIntoAppLib(cursor!!)
        assertNotNull(list)
        assertEquals(list!!.size, 3)
        for (album: Album in list) {
            albumListDao!!.insertAlbum(album)
        }
        assertEquals(albumListDao!!.getCount(), 3)
        list = albumListDao!!.getAllAlbumsRaw();
        assertNotNull(list)
        assertEquals(list.size, 3)

        assertTrue(list[0].equals(albumsList[0]))
        assertTrue(list[1].equals(albumsList[1]))
        assertTrue(list[2].equals(albumsList[2]))

        assertTrue(list[0].tracksList!![0].equals(trackList[0]))
        assertTrue(list[0].tracksList!![1].equals(trackList[1]))
        assertTrue(list[1].tracksList!![0].equals(trackList[2]))
        assertTrue(list[1].tracksList!![1].equals(trackList[3]))
        assertTrue(list[2].tracksList!![0].equals(trackList[4]))

    }

    @Test
    fun areTracksReadingFromAppDBSuccessfully() {
        val cursor = contentResolver!!.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        var list: List<TrackEntity>? = LibraryTransform.transformDeviceTrackLibIntoAppLib(cursor!!)
        assertNotNull(list)
        assertEquals(list!!.size, 5)
        for (track: TrackEntity in list) {
            trackListDao!!.insertTrack(track)
        }
        list = trackListDao!!.getAllTracksRaw()
        assertNotNull(list)
        assertEquals(list.size, 5)
        assertTrue(list[0].equals(trackList[0]))
        assertTrue(list[1].equals(trackList[1]))
        assertTrue(list[2].equals(trackList[2]))
        assertTrue(list[3].equals(trackList[3]))
        assertTrue(list[4].equals(trackList[4]))
    }



    private fun writeAlbums() {
        val albumsCursor: RoboCursor = object : RoboCursor(){}
        albumsCursor.setColumnNames(mutableListOf(
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.ALBUM_ART,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS))

        val albumsArray: Array<Array<Any>> = Array(3) { it -> Array(4, { Any() })}

        albumsArray[0] = arrayOf(1L, "Album1", "Artist1", "testPath1")
        albumsArray[1] = arrayOf(2L, "Album2", "Artist2", "testPath2")
        albumsArray[2] = arrayOf(3L, "Album3", "Artist2", "testPath3")

        albumsList.add(AlbumEntity(1L, "Album1", "Artist1", "testPath1"))
        albumsList.add(AlbumEntity(2L, "Album2", "Artist2", "testPath2"))
        albumsList.add(AlbumEntity(3L, "Album3", "Artist2", "testPath3"))

        albumsCursor.setResults(albumsArray)
        shadowOf(contentResolver).setCursor(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, albumsCursor)
    }

    private fun writeTracks() {
        val trackCursor: RoboCursor = object : RoboCursor(){}

        trackCursor.setColumnNames(mutableListOf(MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.DATA, "albumArtPath", MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.ALBUM_ID))

        val trackArray: Array<Array<Any>> = Array(5){ it -> Array(7, { Any() })}

        trackArray[0] = arrayOf(1L, "Track1", "Artist1", "Album1", "path1", "content://media/external/audio/albumart/1", 5L, 1L)
        trackArray[1] = arrayOf(2L, "Track2", "Artist1", "Album1", "path2", "content://media/external/audio/albumart/1", 5L, 1L)
        trackArray[2] = arrayOf(3L, "Track3", "Artist3", "Album2", "path3", "content://media/external/audio/albumart/2", 4L, 2L)
        trackArray[3] = arrayOf(4L, "Track4", "Artist3", "Album2", "path4", "content://media/external/audio/albumart/2", 1L, 2L)
        trackArray[4] = arrayOf(5L, "Track5", "Artist3", "Album2", "path5", "content://media/external/audio/albumart/3", 77L, 3L)

        trackList.add(TrackEntity(1L, "Track1", "Artist1", "Album1", "path1", "content://media/external/audio/albumart/1",5L, 1L))
        trackList.add(TrackEntity(2L, "Track2", "Artist1", "Album1", "path2", "content://media/external/audio/albumart/1",5L, 1L))
        trackList.add(TrackEntity(3L, "Track3", "Artist3", "Album2", "path3", "content://media/external/audio/albumart/2",4L, 2L))
        trackList.add(TrackEntity(4L, "Track4", "Artist3", "Album2", "path4", "content://media/external/audio/albumart/2",1L, 2L))
        trackList.add(TrackEntity(5L, "Track5", "Artist3", "Album2", "path5", "content://media/external/audio/albumart/3",77L, 3L))

        trackCursor.setResults(trackArray)
        shadowOf(contentResolver).setCursor(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, trackCursor)
    }

}