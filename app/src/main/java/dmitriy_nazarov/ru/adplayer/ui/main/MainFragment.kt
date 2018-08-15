package dmitriy_nazarov.ru.adplayer.ui.main

import dmitriy_nazarov.ru.adplayer.features.list.RecyclerFragment
import dmitriy_nazarov.ru.adplayer.ui.main.list.Item
import dmitriy_nazarov.ru.adplayer.ui.main.list.MainFragmentListAdapter
import dmitriy_nazarov.ru.adplayer.ui.main.list.MainFragmentListViewHolder

/**
 * Created by Dmitry Nazarov on 16-Aug-18.
 */
open class MainFragment : RecyclerFragment<Item, MainFragmentListViewHolder, MainFragmentListAdapter>() {

    override fun instanceRecyclerAdapter(): MainFragmentListAdapter? {
        val list: MutableList<Item> = mutableListOf(Item("Spaced Out", "Khail"), Item("Spaced Out", "Khail"), Item("Spaced Out", "Khail"))
        return MainFragmentListAdapter(list)
    }


}