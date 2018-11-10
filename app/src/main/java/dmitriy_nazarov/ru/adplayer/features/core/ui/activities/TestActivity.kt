package dmitriy_nazarov.ru.adplayer.features.core.ui.activities

import android.os.Bundle
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.core.ui.activities.BaseActivity
import dmitriy_nazarov.ru.adplayer.features.core.ui.fragments.BaseFragment

class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    fun launchFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment, fragment.getFragmentTag()).commit()
    }

    companion object {

        const val FRAGMENT_LAUNCH_WAIT_TIME_MILLISECOND = 1000L

        const val ACTIVITY_LAUNCH_WAIT_TIME_MILLISECOND = 500L

    }

}