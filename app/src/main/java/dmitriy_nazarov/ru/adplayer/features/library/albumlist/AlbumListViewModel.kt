package dmitriy_nazarov.ru.adplayer.features.library.albumlist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.Album

class AlbumListViewModel : ViewModel() {

    private lateinit var lifecycleOwner: LifecycleOwner

    private lateinit var albumListRepository: AlbumListRepository

    fun init(lifecycleOwner: LifecycleOwner, albumListRepository: AlbumListRepository) {
        this.lifecycleOwner = lifecycleOwner
        this.albumListRepository = albumListRepository
    }

    fun getAllAlbums(observer: Observer<List<Album>>) {
        albumListRepository.getAllAlbums().observe(lifecycleOwner, observer)
    }

}