package dmitriy_nazarov.ru.adplayer

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dmitriy_nazarov.ru.adplayer.dagger.DaggerAppComponent
import javax.inject.Inject

class ADPlayerApp : Application(), HasActivityInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    companion object {

        fun isTestMode(): Boolean {
            return BuildConfig.fakeData
        }
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

}