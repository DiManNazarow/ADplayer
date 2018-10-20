package dmitriy_nazarov.ru.adplayer.features.library.tracklist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track

class TrackListViewModel : ViewModel() {

    private lateinit var lifecycleOwner: LifecycleOwner

    private lateinit var trackListRepository: TrackListRepository

    fun init(lifecycleOwner: LifecycleOwner, trackListRepository: TrackListRepository) {
        this.lifecycleOwner = lifecycleOwner
        this.trackListRepository = trackListRepository
    }

    fun getAllTrack(observer: Observer<List<Track>>) {
        trackListRepository.getAllTracks().observe(lifecycleOwner, observer)
    }

}