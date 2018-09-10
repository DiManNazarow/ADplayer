package dmitriy_nazarov.ru.adplayer.features.tracklist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import dmitriy_nazarov.ru.adplayer.ADPlayerApp
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.TrackEntity

object TrackListRepository {

    fun getAllTracksTest(): LiveData<List<Track>> {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().getAllTracks()
    }

    fun getAllTracks(): LiveData<List<Track>> {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().getAllTracks()
    }

    fun insertTracks(tracksList: List<TrackEntity>) {
        for (track: TrackEntity in tracksList){
            AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().insertTrack(track)
        }
    }

    fun insertTrack(track: TrackEntity) {
        AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().insertTrack(track)
    }

    fun getCount(): Int {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().getCount()
    }

}