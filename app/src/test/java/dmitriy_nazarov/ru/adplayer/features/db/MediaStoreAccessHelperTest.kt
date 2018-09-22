package dmitriy_nazarov.ru.adplayer.features.db

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import dmitriy_nazarov.ru.adplayer.TestCursorHelper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class MediaStoreAccessHelperTest {

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
    fun isAllTracksCursorCreatingSuccessfully() {
        val cursor: Cursor? = MediaStoreAccessHelper.getAllTracks(context)
        assertNotNull(cursor)
        assertNotEquals(-1, cursor!!.getColumnIndex(MediaStore.Audio.Media._ID))
        assertNotEquals(-1, cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
        assertNotEquals(-1, cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
        assertNotEquals(-1, cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
        assertNotEquals(-1, cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
        assertNotEquals(-1, cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
        assertNotEquals(-1, cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
    }

    @Test
    fun isAllAlbumsCursorCreatingSuccessfully() {
        val cursor: Cursor? = MediaStoreAccessHelper.getAllAlbums(context)
        assertNotNull(cursor)
        assertNotEquals(-1, cursor!!.getColumnIndex(MediaStore.Audio.Albums._ID))
        assertNotEquals(-1, cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST))
        assertNotEquals(-1, cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM))
        assertNotEquals(-1, cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))
    }

    private fun writeAlbums() {
        Shadows.shadowOf(contentResolver).setCursor(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, TestCursorHelper.getCursorWithAlbums())
    }

    private fun writeTracks() {
        Shadows.shadowOf(contentResolver).setCursor(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, TestCursorHelper.getCursorWithTracks())
    }

}