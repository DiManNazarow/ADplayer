package dmitriy_nazarov.ru.adplayer.features.albumlist

import android.arch.lifecycle.LiveData
import dmitriy_nazarov.ru.adplayer.ADPlayerApp
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.AlbumEntity
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase

object AlbumListRepository {

    fun insertAlbum(album: AlbumEntity) {
        AppDatabase.getInstance(ADPlayerApp.context)!!.albumListDao().insertAlbum(album)
    }

    fun insertAlbums(albums: List<AlbumEntity>) {
        for (album: AlbumEntity in albums){
            insertAlbum(album)
        }
    }

    fun getAllAlbums(): LiveData<List<Album>> {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.albumListDao().getAllAlbums()
    }

    fun getAllAlbumsRaw(): List<Album>? {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.albumListDao().getAllAlbumsRaw()
    }

    fun getCount(): Int {
        return AppDatabase.getInstance(ADPlayerApp.context)!!.albumListDao().getCount()
    }

    fun deleteAll() {
        AppDatabase.getInstance(ADPlayerApp.context)!!.albumListDao().deleteAll()
    }

}