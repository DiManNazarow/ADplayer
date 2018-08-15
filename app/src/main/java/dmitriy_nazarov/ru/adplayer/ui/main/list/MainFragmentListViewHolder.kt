package dmitriy_nazarov.ru.adplayer.ui.main.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.list.BaseRecyclerViewHolder
import kotlinx.android.synthetic.main.fragment_recycler.*

/**
 * Created by Dmitry Nazarov on 16-Aug-18.
 */
class MainFragmentListViewHolder : BaseRecyclerViewHolder<Item> {

    private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
    private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)

    constructor(rootView: ViewGroup) : super(LayoutInflater.from(rootView.context).inflate(R.layout.layout_main_fragment_list_view_holder, rootView, false)) {

    }
    override fun fill() {
        titleTextView.setText(model?.title)
        nameTextView.setText(model?.name)
    }


}