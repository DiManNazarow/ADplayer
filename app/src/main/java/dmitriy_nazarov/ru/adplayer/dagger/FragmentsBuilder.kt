package dmitriy_nazarov.ru.adplayer.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumsListFragment
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.TrackListFragment
import dmitriy_nazarov.ru.adplayer.features.start.StartFragment
import dmitriy_nazarov.ru.adplayer.features.start.StartFragmentModule

@Module
abstract class FragmentsBuilder {

    @ContributesAndroidInjector(modules = arrayOf(StartFragmentModule::class))
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun contributeTrackListFragment(): TrackListFragment

    @ContributesAndroidInjector
    abstract fun contributeAlbumsListFragment(): AlbumsListFragment

}