package dmitriy_nazarov.ru.adplayer.features.library.tracklist

import dmitriy_nazarov.ru.adplayer.TestDataManager
import dmitriy_nazarov.ru.adplayer.features.core.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class TrackListRepositoryTest {

    private lateinit var trackListRepository: TrackListRepository

    @Test
    fun testTrackListRepository() {
        trackListRepository = TrackListRepository(AppDatabase.getInstance(ShadowApplication.getInstance().applicationContext)!!)
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
        trackListRepository.deleteAll()
        trackListRepository.insertTracks(TestDataManager.tracksList)
        val tracks: List<Track>? = trackListRepository.getAllTracksRaw()
        assertNotNull(tracks)
        assertEquals(tracks!!.size, TestDataManager.tracksList.size)
        for (i in TestDataManager.tracksList.indices) {
            assertEquals(tracks[i], TestDataManager.tracksList[i])
        }
    }

    private fun deleteAll() {
        trackListRepository.deleteAll()
        assertEquals(0, trackListRepository.getCount())
    }

    private fun insertTracks() {
        trackListRepository.deleteAll()
        trackListRepository.insertTracks(TestDataManager.tracksList)
        assertEquals(TestDataManager.tracksList.size, trackListRepository.getCount())
    }

    private fun insertTrack() {
        trackListRepository.deleteAll()
        trackListRepository.insertTrack(TestDataManager.tracksList.get(0))
        assertEquals(1, trackListRepository.getCount())
    }

    private fun getCount() {
        assertNotEquals(-1, trackListRepository.getCount())
    }

}