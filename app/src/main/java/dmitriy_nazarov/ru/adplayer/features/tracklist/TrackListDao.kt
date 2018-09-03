package dmitriy_nazarov.ru.adplayer.features.tracklist

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track

@Dao
interface TrackListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: Track)

    @Query("SELECT * FROM Track WHERE Track.id = :id")
    fun getTrack(id: Long): Track

    @Query("SELECT * FROM Track")
    fun getAllTracks(): LiveData<List<Track>>

}