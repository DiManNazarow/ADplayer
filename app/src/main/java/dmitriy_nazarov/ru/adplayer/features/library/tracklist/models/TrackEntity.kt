package dmitriy_nazarov.ru.adplayer.features.library.tracklist.models

import android.arch.persistence.room.*
import dmitriy_nazarov.ru.adplayer.features.livedata.BaseModel
import dmitriy_nazarov.ru.adplayer.utils.TextUtils

@Entity(tableName = "TrackEntity")
open class TrackEntity(
        @PrimaryKey var id: Long?,
        var trackTitle: String?,
        var trackArtist: String?,
        var trackAlbum: String?,
        var trackFilePath: String?,
        var albumArtPath: String?,
        var duration: Long?,
        @ColumnInfo(index = true) var albumId: Long?) : BaseModel() {


    @Ignore
    constructor(id: Long? , trackTitle: String?, trackArtist: String?) : this(id, trackTitle, trackArtist, null, null, null, null, null)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val that = other as TrackEntity
        return id == that.id && TextUtils.isTextEquals(trackTitle, that.trackTitle)
                && TextUtils.isTextEquals(trackArtist, that.trackArtist) && TextUtils.isTextEquals(trackAlbum, that.trackAlbum)
                && TextUtils.isTextEquals(trackFilePath, that.trackFilePath) && TextUtils.isTextEquals(albumArtPath, that.albumArtPath) && duration == that.duration
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (trackTitle?.hashCode() ?: 0)
        result = 31 * result + (trackArtist?.hashCode() ?: 0)
        result = 31 * result + (trackAlbum?.hashCode() ?: 0)
        result = 31 * result + (trackFilePath?.hashCode() ?: 0)
        result = 31 * result + (albumArtPath?.hashCode() ?: 0)
        result = 31 * result + (duration?.hashCode() ?: 0)
        result = 31 * result + (albumId?.hashCode() ?: 0)
        return result
    }

    fun toArray(): Array<Any?> {
        return arrayOf(id, trackTitle, trackArtist, trackAlbum, trackFilePath, albumArtPath, duration, albumId)
    }

}