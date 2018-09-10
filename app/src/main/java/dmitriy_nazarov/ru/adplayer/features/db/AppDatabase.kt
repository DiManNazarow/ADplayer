package dmitriy_nazarov.ru.adplayer.features.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dmitriy_nazarov.ru.adplayer.ADPlayerApp
import dmitriy_nazarov.ru.adplayer.features.albumlist.AlbumListDao
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.AlbumEntity
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListDao
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.TrackEntity

@Database(entities = arrayOf(TrackEntity::class, AlbumEntity::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trackListDao(): TrackListDao

    abstract fun albumListDao(): AlbumListDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context?): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    if (ADPlayerApp.isTestMode()) {
                        instance = Room.inMemoryDatabaseBuilder(context!!, AppDatabase::class.java).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    } else {
                        instance = Room.databaseBuilder(context!!.applicationContext, AppDatabase::class.java, "app_database.db").fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    }
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }

}