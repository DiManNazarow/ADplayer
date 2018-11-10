package dmitriy_nazarov.ru.adplayer.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dmitriy_nazarov.ru.adplayer.features.library.LibraryFragment
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumsListFragment
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.TrackListFragment
import dmitriy_nazarov.ru.adplayer.features.start.StartFragment
import dmitriy_nazarov.ru.adplayer.features.start.StartFragmentModule

@Module
abstract class FragmentsBuilder {

    @ContributesAndroidInjector(modules = arrayOf(StartFragmentModule::class))
    abstract fun buildStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun buildrackListFragment(): TrackListFragment

    @ContributesAndroidInjector
    abstract fun buildAlbumsListFragment(): AlbumsListFragment

    @ContributesAndroidInjector
    abstract fun buildLibraryFragment(): LibraryFragment

}