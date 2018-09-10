package dmitriy_nazarov.ru.adplayer

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class ADPlayerApp : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        var context: Context? = null

        fun isTestMode(): Boolean {
            return BuildConfig.testConfig
        }

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}