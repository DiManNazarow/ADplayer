package dmitriy_nazarov.ru.adplayer.features.tracklist.recyclerview

import android.view.ViewGroup
import dmitriy_nazarov.ru.adplayer.features.list.BaseRecyclerAdapter
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track

/**
 * Created by Dmitry Nazarov on 16-Aug-18.
 */
class TrackListRecyclerAdapter(items: MutableList<Track>?) : BaseRecyclerAdapter<Track, TrackListViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackListViewHolder {
        return TrackListViewHolder(parent)
    }

}