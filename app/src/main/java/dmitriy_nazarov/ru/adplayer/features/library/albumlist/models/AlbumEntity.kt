package dmitriy_nazarov.ru.adplayer.features.library.albumlist.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import dmitriy_nazarov.ru.adplayer.features.livedata.BaseModel
import dmitriy_nazarov.ru.adplayer.utils.TextUtils

@Entity(tableName = "AlbumEntity")
open class AlbumEntity(
        @PrimaryKey var id: Long?,
        var albumArtist: String?,
        var albumName: String?,
        var albumArtPath: String?) : BaseModel() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val that = other as AlbumEntity
        return id!!.equals(that.id) && TextUtils.isTextEquals(albumArtist, that.albumArtist) && TextUtils.isTextEquals(albumName, that.albumName) && TextUtils.isTextEquals(getFullAlbumArtPath(), that.getFullAlbumArtPath())
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (albumArtist?.hashCode() ?: 0)
        result = 31 * result + (albumName?.hashCode() ?: 0)
        result = 31 * result + (albumArtPath?.hashCode() ?: 0)
        return result
    }

    fun toArray(): Array<Any?> {
        return arrayOf(id, albumArtist, albumName,  albumArtPath)
    }

    fun getFullAlbumArtPath(): String? {
        if (albumArtPath != null && !albumArtPath!!.contains("file://")) {
            return String.format("file://%s", albumArtPath)
        } else {
            return albumArtPath
        }
    }


}