package dmitriy_nazarov.ru.adplayer.features.library.albumlist

import android.arch.lifecycle.LiveData
import dmitriy_nazarov.ru.adplayer.ADPlayerApp
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.AlbumEntity
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import javax.inject.Inject

class AlbumListRepository @Inject constructor(val appDatabase: AppDatabase) {

    fun insertAlbum(album: AlbumEntity) {
        appDatabase.albumListDao().insertAlbum(album)
    }

    fun insertAlbums(albums: List<AlbumEntity>) {
        for (album: AlbumEntity in albums){
            insertAlbum(album)
        }
    }

    fun getAllAlbums(): LiveData<List<Album>> {
        return appDatabase.albumListDao().getAllAlbums()
    }

    fun getAllAlbumsRaw(): List<Album>? {
        return appDatabase.albumListDao().getAllAlbumsRaw()
    }

    fun getCount(): Int {
        return appDatabase.albumListDao().getCount()
    }

    fun deleteAll() {
        appDatabase.albumListDao().deleteAll()
    }

}