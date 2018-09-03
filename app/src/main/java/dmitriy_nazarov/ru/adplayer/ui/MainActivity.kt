package dmitriy_nazarov.ru.adplayer.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListFragment
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppDatabase.getInstance(applicationContext)!!.trackListDao().insertTrack(Track("df", "fdf"))
        AppDatabase.getInstance(applicationContext)!!.trackListDao().insertTrack(Track("dfdd", "fdddf"))
        AppDatabase.getInstance(applicationContext)!!.trackListDao().insertTrack(Track("d111f", "fd111f"))

        supportFragmentManager.beginTransaction().add(R.id.main, TrackListFragment()).commit()
    }
}
