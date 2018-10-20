package dmitriy_nazarov.ru.adplayer.features.library.tracklist

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.TrackEntity

@Dao
interface TrackListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: TrackEntity)

    @Query("SELECT * FROM TrackEntity WHERE TrackEntity.id = :id")
    fun getTrack(id: Long): Track

    @Query("SELECT * FROM TrackEntity")
    fun getAllTracks(): LiveData<List<Track>>

    @Query("SELECT * FROM TrackEntity")
    fun getAllTracksRaw(): List<Track>

    @Query("SELECT COUNT(*) FROM TrackEntity")
    fun getCount(): Int;

    @Query("DELETE FROM TrackEntity")
    fun deleteAll();

}