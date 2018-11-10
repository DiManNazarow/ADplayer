package dmitriy_nazarov.ru.adplayer.utils

import android.support.test.espresso.NoMatchingViewException
import android.view.View
import org.hamcrest.Matcher

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.v7.widget.RecyclerView

class RecyclerViewInteraction<A> private constructor(private var viewMatcher: Matcher<View>) {

    private lateinit var items: List<A>

    companion object {

        fun <A> onRecyclerView(viewMatcher: Matcher<View>): RecyclerViewInteraction<A> {
            return RecyclerViewInteraction(viewMatcher)
        }

    }

    fun withItems(items: List<A>): RecyclerViewInteraction<A> {
        this.items = items
        return this
    }

    fun check(itemViewAssertion: ItemViewAssertion<A>): RecyclerViewInteraction<A> {
        for (i in items.indices) {
            onView(viewMatcher).perform(scrollToPosition<RecyclerView.ViewHolder>(i)).check(RecyclerItemViewAssertion(i, items[i], itemViewAssertion))
        }
        return this
    }

    interface ItemViewAssertion<A> {
        fun check(item: A, view: View, exception: NoMatchingViewException?)
    }

}