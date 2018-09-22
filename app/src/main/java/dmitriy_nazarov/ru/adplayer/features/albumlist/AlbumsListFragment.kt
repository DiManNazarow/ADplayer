package dmitriy_nazarov.ru.adplayer.features.albumlist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.albumlist.models.Album
import dmitriy_nazarov.ru.adplayer.features.albumlist.recyclerview.AlbumListRecyclerAdapter
import dmitriy_nazarov.ru.adplayer.features.list.GridSpaceItemDecoration
import dmitriy_nazarov.ru.adplayer.features.list.ViewModelRecyclerFragment
import kotlinx.android.synthetic.main.fragment_recycler.*

class AlbumsListFragment : ViewModelRecyclerFragment<Album, AlbumListRecyclerAdapter, AlbumListViewModel>(), Observer<List<Album>> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel!!.init(this)
        viewModel!!.getAllAlbums(this)
        recycler_view.addItemDecoration(GridSpaceItemDecoration(activity!!.resources.getDimensionPixelSize(R.dimen.grid_list_spaces)))
    }

    override fun getRecyclerLayoutManager() : (RecyclerView.LayoutManager) = GridLayoutManager(activity, 2)

    override fun instanceViewModel(): Class<AlbumListViewModel> {
        return AlbumListViewModel::class.java
    }

    override fun instanceRecyclerAdapter(): AlbumListRecyclerAdapter {
        return AlbumListRecyclerAdapter(null)
    }

    override fun onChanged(albums: List<Album>?) {
        addItems(albums as ArrayList, false)
    }
}