package dmitriy_nazarov.ru.adplayer.features.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListDao
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track

@Database(entities = arrayOf(Track::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trackListDao(): TrackListDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context?): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context!!.applicationContext, AppDatabase::class.java, "app_database.db").allowMainThreadQueries().build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }

}