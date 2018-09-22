package dmitriy_nazarov.ru.adplayer.features.albumlist

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.AlbumEntity

@Dao
interface AlbumListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: AlbumEntity)

    @Transaction
    @Query("SELECT * FROM AlbumEntity")
    fun getAllAlbums(): LiveData<List<Album>>

    @Transaction
    @Query("SELECT * FROM AlbumEntity")
    fun getAllAlbumsRaw(): List<Album>

    @Query("SELECT COUNT(*) FROM AlbumEntity")
    fun getCount(): Int

    @Query("DELETE FROM AlbumEntity")
    fun deleteAll()

}