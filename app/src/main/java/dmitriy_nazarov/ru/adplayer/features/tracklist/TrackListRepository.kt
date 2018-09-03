package dmitriy_nazarov.ru.adplayer.features.tracklist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import dmitriy_nazarov.ru.adplayer.ADPlayerApp
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track

class TrackListRepository {

    companion object {

        private var instance: TrackListRepository? = null

        fun getInstance(): TrackListRepository? {
            if (instance == null){
                synchronized(TrackListRepository::class) {
                    instance = TrackListRepository()
                }
            }
            return instance
        }
    }


    fun getAllTracksTest(): MutableLiveData<List<Track>> {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().getAllTracks() as MutableLiveData<List<Track>>
    }

    fun getAllTracks(): LiveData<List<Track>> {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().getAllTracks()
    }

    fun insertTracks(tracksList: List<Track>) {
        for (track: Track in tracksList){
            AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().insertTrack(track)
        }
    }

    fun insertTrack(track: Track) {
        AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().insertTrack(track)
    }

}