package dmitriy_nazarov.ru.adplayer.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dmitriy_nazarov.ru.adplayer.ui.activities.main.MainActivity

@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector(modules = arrayOf(FragmentsBuilder::class))
    abstract fun buildMainActivity(): MainActivity

}