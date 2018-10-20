package dmitriy_nazarov.ru.adplayer.features.tracklist.models

open class Track(id: Long?,
                 trackTitle: String?,
                 trackArtist: String?,
                 trackAlbum: String?,
                 trackFilePath: String?,
                 albumArtPath: String?,
                 duration: Long?,
                 albumId: Long?) : TrackEntity(id, trackTitle, trackArtist, trackAlbum, trackFilePath, albumArtPath, duration, albumId) {

}