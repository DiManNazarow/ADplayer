package dmitriy_nazarov.ru.adplayer.features.core.db

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.support.annotation.VisibleForTesting
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumListRepository
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.AlbumEntity
import android.net.Uri
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.TrackEntity
import dmitriy_nazarov.ru.adplayer.utils.TextUtils


val sArtworkUri = Uri
        .parse("content://media/external/audio/albumart")

class LibraryTransform {

    companion object {

        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        fun transformDeviceTrackLibIntoAppLib(context: Context): List<TrackEntity>? {
            var trackList: ArrayList<Track>? = null
            val trackCursor: Cursor? = MediaStoreAccessHelper.getAllTracks(context)
            if (trackCursor != null) {
                trackCursor.moveToFirst()
                try {
                    trackList = ArrayList()
                    do {

                        val id: Long = trackCursor.getLong(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                        val trackTitle: String? = trackCursor.getString(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                        val trackArtist: String? = trackCursor.getString(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                        val trackAlbum: String? = trackCursor.getString(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))
                        val trackFilePath: String? = trackCursor.getString(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                        val duration: Long? = trackCursor.getLong(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                        val albumId: Long? = trackCursor.getLong(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID))

                        val albumArtPath: String? = ContentUris.withAppendedId(MediaStoreAccessHelper.artworkUri, albumId!!).toString()

                        val track = Track(id, trackTitle, trackArtist, trackAlbum, trackFilePath, albumArtPath, duration, albumId)
                        trackList.add(track)

                    } while (trackCursor.moveToNext())

                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
            return trackList
        }

        fun transformDeviceTrackLibIntoAppLib(trackCursor: Cursor?): List<TrackEntity>? {
            var trackList: ArrayList<Track>? = null
            if (trackCursor != null) {
                trackCursor.moveToFirst()
                try {
                    trackList = ArrayList()
                    do {

                        val id: Long = trackCursor.getLong(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                        val trackTitle: String? = trackCursor.getString(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                        val trackArtist: String? = trackCursor.getString(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                        val trackAlbum: String? = trackCursor.getString(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))
                        val trackFilePath: String? = trackCursor.getString(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                        val duration: Long? = trackCursor.getLong(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                        val albumId: Long? = trackCursor.getLong(trackCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID))

                        val albumArtPath: String? = ContentUris.withAppendedId(MediaStoreAccessHelper.artworkUri, albumId!!).toString()

                        val track = Track(id, trackTitle, trackArtist, trackAlbum, trackFilePath, albumArtPath, duration, albumId)
                        trackList.add(track)

                    } while (trackCursor.moveToNext())

                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
            return trackList
        }

        @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
        fun transformDeviceAlbumLibIntoAppLib(context: Context, albumListRepository: AlbumListRepository): List<AlbumEntity>? {
            var albumList: ArrayList<Album>? = null
            val albumCursor: Cursor? = MediaStoreAccessHelper.getAllAlbums(context)
            if (albumCursor != null) {
                albumCursor.moveToFirst()
                try {
                    albumList = ArrayList()
                    do {

                        val id: Long = albumCursor.getLong(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID))
                        var albumName: String = albumCursor.getString(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM))
                        val albumArtist: String = albumCursor.getString(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST))
                        val albumArtPath: String? = albumCursor.getString(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART))

                        val albumNameByte = albumName.toByteArray()
                        albumName = String(albumNameByte, Charsets.UTF_8)

                        val album = Album(id, albumName, albumArtist, albumArtPath)
                        albumList.add(album)

                    } while (albumCursor.moveToNext())

                    albumListRepository.insertAlbums(albumList)

                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
            return albumList
        }


        fun transformDeviceAlbumLibIntoAppLib(albumCursor: Cursor?): List<AlbumEntity>? {
            var albumList: ArrayList<Album>? = null
            if (albumCursor != null) {
                albumCursor.moveToFirst()
                try {
                    albumList = ArrayList()
                    do {

                        val id: Long = albumCursor.getLong(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID))
                        var albumName: String = albumCursor.getString(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM))
                        val albumArtist: String = albumCursor.getString(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST))

                        val albumArtPath: String? = albumCursor.getString(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART))

                        var albumArtPathFull: String? = null
                        if (!TextUtils.isTextEmpty(albumArtPath)) {
                            albumArtPathFull = "file://" + albumArtPath
                        }

                        val albumNameByte = albumName.toByteArray()
                        albumName = String(albumNameByte, Charsets.UTF_8)

                        val album = Album(id, albumName, albumArtist, albumArtPathFull)
                        albumList.add(album)

                    } while (albumCursor.moveToNext())

                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
            return albumList
        }


    }

}