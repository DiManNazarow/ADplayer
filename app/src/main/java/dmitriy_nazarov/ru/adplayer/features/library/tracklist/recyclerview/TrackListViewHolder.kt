package dmitriy_nazarov.ru.adplayer.features.library.tracklist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.features.core.list.BaseRecyclerViewHolder

/**
 * Created by Dmitry Nazarov on 16-Aug-18.
 */
class TrackListViewHolder(rootView: ViewGroup) : BaseRecyclerViewHolder<Track>(LayoutInflater.from(rootView.context).inflate(R.layout.layout_main_fragment_list_view_holder, rootView, false)) {

    private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
    private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)

    override fun fill() {
        titleTextView.text = model?.trackTitle
        nameTextView.text = model?.trackArtist
    }

}