package dmitriy_nazarov.ru.adplayer.utils

import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.AlbumEntity
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.TrackEntity

class TestDataManager {

    companion object {

        val tracksList: List<TrackEntity> = listOf(
                TrackEntity(1L, "Track1", "Artist1", "Album1", "path1", "content://media/external/audio/albumart/1",5L, 1L),
                TrackEntity(2L, "Track2", "Artist1", "Album1", "path2", "content://media/external/audio/albumart/1",5L, 1L),
                TrackEntity(3L, "Track3", "Artist3", "Album2", "path3", "content://media/external/audio/albumart/2",4L, 2L),
                TrackEntity(4L, "Track4", "Artist3", "Album2", "path4", "content://media/external/audio/albumart/2",1L, 2L),
                TrackEntity(5L, "Track5", "Artist3", "Album2", "path5", "content://media/external/audio/albumart/3",7L, 3L))

        val albumsList: List<AlbumEntity> = listOf(
                AlbumEntity(1L, "Album1", "Artist1", "testPath1"),
                AlbumEntity(2L, "Album2", "Artist2", "testPath2"),
                AlbumEntity(3L, "Album3", "Artist2", "testPath3"))

    }

}