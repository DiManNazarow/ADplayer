package dmitriy_nazarov.ru.adplayer.features.core.ui.activities

import dagger.android.support.DaggerAppCompatActivity
import dagger.android.AndroidInjection
import android.os.Bundle

abstract class BaseActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

}