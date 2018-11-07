package dmitriy_nazarov.ru.adplayer.features.library.albumlist.recyclerview

import android.view.ViewGroup
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.core.list.BaseRecyclerAdapter

class AlbumListRecyclerAdapter(items: MutableList<Album>?) : BaseRecyclerAdapter<Album, AlbumListViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        return AlbumListViewHolder(parent)
    }


}