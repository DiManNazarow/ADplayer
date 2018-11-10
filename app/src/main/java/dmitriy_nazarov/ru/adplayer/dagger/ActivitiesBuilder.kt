package dmitriy_nazarov.ru.adplayer.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dmitriy_nazarov.ru.adplayer.features.core.ui.activities.TestActivity
import dmitriy_nazarov.ru.adplayer.ui.main.MainActivity

@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector(modules = arrayOf(FragmentsBuilder::class))
    abstract fun buildMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentsBuilder::class))
    abstract fun buildTestActivity(): TestActivity

}