package dmitriy_nazarov.ru.adplayer.utils

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.PerformException;
import android.support.test.espresso.util.HumanReadables
import android.support.v7.widget.RecyclerView
import android.view.View
import java.lang.IllegalStateException

class RecyclerItemViewAssertion<A>(var position: Int,
                                   var item: A,
                                   private var itemViewAssertion: RecyclerViewInteraction.ItemViewAssertion<A>) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        val recyclerView: RecyclerView = view as RecyclerView
        val recyclerViewViewHolder: RecyclerView.ViewHolder? = recyclerView.findViewHolderForLayoutPosition(position)
        if (recyclerViewViewHolder == null) {
            throw (PerformException.Builder())
                    .withActionDescription(toString())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(IllegalStateException("No view holder at position: " + position))
                    .build()
        } else {
            val viewAtPosition: View = recyclerViewViewHolder.itemView
            itemViewAssertion.check(item, viewAtPosition, noViewFoundException)
        }
    }
}