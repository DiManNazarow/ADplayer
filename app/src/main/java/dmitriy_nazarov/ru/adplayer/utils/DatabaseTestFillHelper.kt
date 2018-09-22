package dmitriy_nazarov.ru.adplayer.utils

import android.content.Context
import dmitriy_nazarov.ru.adplayer.features.albumlist.AlbumListRepository
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListRepository
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.TrackEntity

class DatabaseTestFillHelper {

    companion object {

        fun fill(context: Context) {
            AlbumListRepository.insertAlbums(TestDataManager.albumsList)
            TrackListRepository.insertTracks(TestDataManager.tracksList)
        }

    }

}