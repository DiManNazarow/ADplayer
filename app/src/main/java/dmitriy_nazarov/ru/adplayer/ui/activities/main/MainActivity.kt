package dmitriy_nazarov.ru.adplayer.ui.activities.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.ui.activities.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
