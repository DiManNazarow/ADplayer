package dmitriy_nazarov.ru.adplayer.features.library.tracklist

import dmitriy_nazarov.ru.adplayer.TestDataManager
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListRepository
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TrackListRepositoryTest {

    @Test
    fun testTrackListRepository() {
        getCount()
        insertTrack()
        insertTracks()
        deleteAll()
        getAllTracksRaw()
    }

    @After
    fun closeDB() {
        AppDatabase.destroyInstance()
    }

    private fun getAllTracksRaw() {
        TrackListRepository.deleteAll()
        TrackListRepository.insertTracks(TestDataManager.tracksList)
        val tracks: List<Track>? = TrackListRepository.getAllTracksRaw()
        assertNotNull(tracks)
        assertEquals(tracks!!.size, TestDataManager.tracksList.size)
        for (i in TestDataManager.tracksList.indices) {
            assertEquals(tracks[i], TestDataManager.tracksList[i])
        }
    }

    private fun deleteAll() {
        TrackListRepository.deleteAll()
        assertEquals(0, TrackListRepository.getCount())
    }

    private fun insertTracks() {
        TrackListRepository.deleteAll()
        TrackListRepository.insertTracks(TestDataManager.tracksList)
        assertEquals(TestDataManager.tracksList.size, TrackListRepository.getCount())
    }

    private fun insertTrack() {
        TrackListRepository.deleteAll()
        TrackListRepository.insertTrack(TestDataManager.tracksList.get(0))
        assertEquals(1, TrackListRepository.getCount())
    }

    private fun getCount() {
        assertNotEquals(-1, TrackListRepository.getCount())
    }

}