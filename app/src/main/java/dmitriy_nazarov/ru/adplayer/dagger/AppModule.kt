package dmitriy_nazarov.ru.adplayer.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import dmitriy_nazarov.ru.adplayer.features.core.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumListRepository
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.TrackListRepository
import dmitriy_nazarov.ru.adplayer.features.start.StartFragmentModule
import javax.inject.Singleton

@Module(includes = arrayOf(StartFragmentModule::class))
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application): AppDatabase {
        return AppDatabase.getInstance(context)!!
    }

    @Provides
    @Singleton
    fun provideTrackListRepository(context: Application): TrackListRepository {
        return TrackListRepository(AppDatabase.getInstance(context)!!)
    }

    @Provides
    @Singleton
    fun provideAlbumListRepository(context: Application): AlbumListRepository {
        return AlbumListRepository(AppDatabase.getInstance(context)!!)
    }


}