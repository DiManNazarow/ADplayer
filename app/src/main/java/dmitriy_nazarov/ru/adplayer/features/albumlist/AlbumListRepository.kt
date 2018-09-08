package dmitriy_nazarov.ru.adplayer.features.albumlist

import android.arch.lifecycle.LiveData
import dmitriy_nazarov.ru.adplayer.ADPlayerApp
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase

object AlbumListRepository {

    fun insertAlbum(album: Album) {
        AppDatabase.getInstance(ADPlayerApp.context)!!.albumListDao().insertAlbum(album)
    }

    fun insertAlbums(albums: List<Album>) {
        for (album: Album in albums){
            insertAlbum(album)
        }
    }

    fun getAllAlbums(): LiveData<List<Album>> {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.albumListDao().getAllAlbums()
    }

}