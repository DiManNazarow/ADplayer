package dmitriy_nazarov.ru.adplayer.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.ui.main.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager.beginTransaction().add(R.id.main, MainFragment()).commit()
    }
}
