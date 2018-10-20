package dmitriy_nazarov.ru.adplayer.features.library.albumlist.models

import android.arch.persistence.room.Relation
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.TrackEntity

class Album(id: Long?,
                 albumArtist: String?,
                 albumName: String?,
                 albumArtPath: String?) : AlbumEntity(id, albumArtist, albumName, albumArtPath) {

    @Relation(parentColumn = "id", entityColumn = "albumId")
    var tracksList: List<TrackEntity>? = null

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (tracksList?.hashCode() ?: 0)
        return result
    }

}