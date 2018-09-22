package dmitriy_nazarov.ru.adplayer.features.albumlist.recyclerview

import android.view.ViewGroup
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.list.BaseRecyclerAdapter

class AlbumListRecyclerAdapter(items: MutableList<Album>?) : BaseRecyclerAdapter<Album, AlbumListViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        return AlbumListViewHolder(parent)
    }


}