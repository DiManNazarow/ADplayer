package dmitriy_nazarov.ru.adplayer.features.albumlist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album

class AlbumListViewModel : ViewModel() {

    private lateinit var lifecycleOwner: LifecycleOwner

    fun init(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    fun getAllAlbums(observer: Observer<List<Album>>) {
        AlbumListRepository.getAllAlbums().observe(lifecycleOwner, observer)
    }

}