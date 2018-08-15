package dmitriy_nazarov.ru.adplayer.ui.main.list

import android.view.ViewGroup
import dmitriy_nazarov.ru.adplayer.features.list.BaseRecyclerAdapter

/**
 * Created by Dmitry Nazarov on 16-Aug-18.
 */
class MainFragmentListAdapter(items: MutableList<Item>) : BaseRecyclerAdapter<Item, MainFragmentListViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentListViewHolder {
        return MainFragmentListViewHolder(parent)
    }

}