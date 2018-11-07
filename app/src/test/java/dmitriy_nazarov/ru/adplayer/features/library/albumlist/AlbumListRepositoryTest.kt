package dmitriy_nazarov.ru.adplayer.features.library.albumlist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import dmitriy_nazarov.ru.adplayer.TestDataManager
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.core.db.AppDatabase
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowApplication
import org.junit.rules.TestRule



@RunWith(RobolectricTestRunner::class)
class AlbumListRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var albumListRepository: AlbumListRepository

    @Test
    fun testAlbumListRepository() {
        albumListRepository = AlbumListRepository(AppDatabase.getInstance(ShadowApplication.getInstance().applicationContext)!!)
        getCount()
        insertAlbum()
        insertAlbums()
        deleteAll()
        getAllAlbumsRaw()
    }

    @After
    fun closeDB() {
        AppDatabase.destroyInstance()
    }

    private fun insertAlbum() {
        albumListRepository.deleteAll()
        albumListRepository.insertAlbum(TestDataManager.albumsList[0])
        assertEquals(1, albumListRepository.getCount())
    }

    private fun insertAlbums() {
        albumListRepository.deleteAll()
        albumListRepository.insertAlbums(TestDataManager.albumsList)
        assertEquals(TestDataManager.albumsList.size, albumListRepository.getCount())
    }

    private fun getAllAlbumsRaw() {
        albumListRepository.deleteAll()
        albumListRepository.insertAlbums(TestDataManager.albumsList)
        val albums: List<Album>? = albumListRepository.getAllAlbumsRaw()
        assertNotNull(albums)
        assertEquals(albums!!.size, TestDataManager.albumsList.size)
        for (i in TestDataManager.albumsList.indices) {
            assertEquals(albums[i], TestDataManager.albumsList[i])
        }
    }

    private fun getCount() {
        assertNotEquals(-1, albumListRepository.getCount())
    }

    private fun deleteAll() {
        albumListRepository.deleteAll()
        assertEquals(0, albumListRepository.getCount())
    }

}