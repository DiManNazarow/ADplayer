package dmitriy_nazarov.ru.adplayer.utils

import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumListRepository
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListRepository

class DatabaseTestFillHelper {

    companion object {

        fun fill() {
            AlbumListRepository.insertAlbums(TestDataManager.albumsList)
            TrackListRepository.insertTracks(TestDataManager.tracksList)
        }

    }

}