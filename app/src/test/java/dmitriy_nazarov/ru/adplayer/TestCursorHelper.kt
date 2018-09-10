package dmitriy_nazarov.ru.adplayer

import android.provider.MediaStore
import org.robolectric.fakes.RoboCursor

class TestCursorHelper {

    companion object {

        fun getCursorWithTracks(): RoboCursor {
            val trackCursor: RoboCursor = object : RoboCursor(){}

            trackCursor.setColumnNames(mutableListOf(MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.DATA, "albumArtPath", MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.ALBUM_ID))

            val trackOne = TestDataManager.tracksList[0].toArray()
            val trackTwo = TestDataManager.tracksList[1].toArray()
            val trackThree = TestDataManager.tracksList[2].toArray()
            val trackFour = TestDataManager.tracksList[3].toArray()
            val trackFive = TestDataManager.tracksList[4].toArray()

            trackCursor.setResults(arrayOf(trackOne, trackTwo, trackThree, trackFour, trackFive))
            return trackCursor
        }

        fun getCursorWithAlbums(): RoboCursor {
            val albumsCursor: RoboCursor = object : RoboCursor(){}
            albumsCursor.setColumnNames(mutableListOf(
                    MediaStore.Audio.Albums._ID,
                    MediaStore.Audio.Albums.ALBUM,
                    MediaStore.Audio.Albums.ARTIST,
                    MediaStore.Audio.Albums.ALBUM_ART,
                    MediaStore.Audio.Albums.NUMBER_OF_SONGS))

            val albumOne = TestDataManager.albumsList[0].toArray()
            val albumTwo = TestDataManager.albumsList[1].toArray()
            val albumThree = TestDataManager.albumsList[2].toArray()

            albumsCursor.setResults(arrayOf(albumOne, albumTwo, albumThree))
            return albumsCursor
        }

    }

}