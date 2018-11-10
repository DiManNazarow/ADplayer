package dmitriy_nazarov.ru.adplayer.utils

import android.support.test.espresso.Espresso
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import dmitriy_nazarov.ru.adplayer.features.core.ui.activities.TestActivity
import org.junit.Before

abstract class FragmentTest<F : Fragment> {

    protected val activityTestRule: ActivityTestRule<TestActivity> = ActivityTestRule<TestActivity>(TestActivity::class.java)

    @Before
    abstract fun setup()

    fun waitTime(time: Long) {
        Espresso.onView(ViewMatchers.isRoot()).perform(TestUtils.waitFor(time))
    }

    fun findFragment(tag: String): F {
        return activityTestRule.activity.supportFragmentManager.findFragmentByTag(tag) as F
    }

    fun findFragment(supportFragmentManager: FragmentManager, tag: String): F {
        return supportFragmentManager.findFragmentByTag(tag) as F
    }

}