package dmitriy_nazarov.ru.adplayer.features.albumlist

import dmitriy_nazarov.ru.adplayer.TestDataManager
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AlbumListRepositoryTest {

    @Test
    fun testAlbumListRepository() {
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
        AlbumListRepository.deleteAll()
        AlbumListRepository.insertAlbum(TestDataManager.albumsList[0])
        assertEquals(1, AlbumListRepository.getCount())
    }

    private fun insertAlbums() {
        AlbumListRepository.deleteAll()
        AlbumListRepository.insertAlbums(TestDataManager.albumsList)
        assertEquals(TestDataManager.albumsList.size, AlbumListRepository.getCount())
    }

    private fun getAllAlbumsRaw() {
        AlbumListRepository.deleteAll()
        AlbumListRepository.insertAlbums(TestDataManager.albumsList)
        val albums: List<Album>? = AlbumListRepository.getAllAlbumsRaw()
        assertNotNull(albums)
        assertEquals(albums!!.size, TestDataManager.albumsList.size)
        for (i in TestDataManager.albumsList.indices) {
            assertEquals(albums[i], TestDataManager.albumsList[i])
        }
    }

    private fun getCount() {
        assertNotEquals(-1, AlbumListRepository.getCount())
    }

    private fun deleteAll() {
        AlbumListRepository.deleteAll()
        assertEquals(0, AlbumListRepository.getCount())
    }

}