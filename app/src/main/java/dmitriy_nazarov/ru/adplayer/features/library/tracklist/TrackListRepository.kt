package dmitriy_nazarov.ru.adplayer.features.library.tracklist

import android.arch.lifecycle.LiveData
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.TrackEntity
import javax.inject.Inject

class TrackListRepository @Inject constructor(private var appDatabase: AppDatabase) {

    fun getAllTracks(): LiveData<List<Track>> {
        return appDatabase.trackListDao().getAllTracks()
    }

    fun getAllTracksRaw(): List<Track>? {
        return appDatabase.trackListDao().getAllTracksRaw()
    }

    fun insertTracks(tracksList: List<TrackEntity>) {
        for (track: TrackEntity in tracksList){
            appDatabase.trackListDao().insertTrack(track)
        }
    }

    fun insertTrack(track: TrackEntity) {
        appDatabase.trackListDao().insertTrack(track)
    }

    fun getCount(): Int {
        return appDatabase.trackListDao().getCount()
    }

    fun deleteAll() {
        appDatabase.trackListDao().deleteAll()
    }

}