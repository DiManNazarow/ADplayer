package dmitriy_nazarov.ru.adplayer.features.tracklist

import android.arch.lifecycle.LiveData
import android.content.Context
import dmitriy_nazarov.ru.adplayer.ADPlayerApp
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.TrackEntity
import dmitriy_nazarov.ru.adplayer.utils.SingletonHolder

object TrackListRepository /**private constructor(var context: Context)*/{

    //companion object : SingletonHolder<TrackListRepository, Context>(::TrackListRepository)

    fun getAllTracks(): LiveData<List<Track>> {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().getAllTracks()
    }

    fun getAllTracksRaw(): List<Track>? {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().getAllTracksRaw()
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

    fun deleteAll() {
        AppDatabase.getInstance(ADPlayerApp.context)!!.trackListDao().deleteAll()
    }

}