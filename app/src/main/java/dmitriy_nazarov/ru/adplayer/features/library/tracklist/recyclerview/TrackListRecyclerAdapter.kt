package dmitriy_nazarov.ru.adplayer.features.library.tracklist.recyclerview

import android.view.ViewGroup
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.features.list.BaseRecyclerAdapter

/**
 * Created by Dmitry Nazarov on 16-Aug-18.
 */
class TrackListRecyclerAdapter(items: MutableList<Track>?) : BaseRecyclerAdapter<Track, TrackListViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackListViewHolder {
        return TrackListViewHolder(parent)
    }

}