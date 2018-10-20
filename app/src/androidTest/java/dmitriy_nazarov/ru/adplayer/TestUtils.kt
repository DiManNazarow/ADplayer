package dmitriy_nazarov.ru.adplayer

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.view.View
import org.hamcrest.Matcher

class TestUtils {

    companion object {

        fun waitFor(millisec: Long): ViewAction {
            return object : ViewAction {
                override fun getDescription(): String {
                    return "Wait for $millisec milliseconds.";
                }

                override fun getConstraints(): Matcher<View> {
                    return isRoot()
                }

                override fun perform(uiController: UiController?, view: View?) {
                    uiController?.loopMainThreadForAtLeast(millisec);
                }

            }
        }

    }

}