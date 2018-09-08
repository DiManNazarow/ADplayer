package dmitriy_nazarov.ru.adplayer.features.tracklist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track

class TrackListViewModel : ViewModel() {

    var lifecycleOwner: LifecycleOwner? = null

    fun init(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    fun getAllTrack(observer: Observer<List<Track>>) {
        TrackListRepository.getAllTracks().observe(lifecycleOwner!!, observer)
    }

}