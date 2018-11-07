package dmitriy_nazarov.ru.adplayer.features.core.db

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

class MediaStoreAccessHelper {

    companion object {

        val artworkUri = Uri.parse("content://media/external/audio/albumart")

        fun getAllTracks(context: Context, sortOrder: String): Cursor? {
            val contentResolver: ContentResolver = context.contentResolver
            val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            val selection: String = MediaStore.Audio.Media.IS_MUSIC + "!=0"
            return contentResolver.query(uri, null, selection, null, null)
        }

        fun getAllTracks(context: Context): Cursor? {
            return getAllTracks(context, "ASC")
        }

        fun getAllAlbums(context: Context, sortOrder: String): Cursor? {
            val contentResolver: ContentResolver = context.contentResolver
            val projection: Array<String> = arrayOf(MediaStore.Audio.Albums._ID,
                    MediaStore.Audio.Albums.ALBUM,
                    MediaStore.Audio.Albums.ARTIST,
                    MediaStore.Audio.Albums.ALBUM_ART,
                    MediaStore.Audio.Albums.NUMBER_OF_SONGS)
            return contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, projection, null, null, null)
        }

        fun getAllAlbums(context: Context) : Cursor? {
            return getAllAlbums(context, "ASC")
        }

    }

}