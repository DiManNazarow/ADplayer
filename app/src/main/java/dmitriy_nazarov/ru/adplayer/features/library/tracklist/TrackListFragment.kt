package dmitriy_nazarov.ru.adplayer.features.tracklist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import dmitriy_nazarov.ru.adplayer.features.list.ViewModelRecyclerFragment
import dmitriy_nazarov.ru.adplayer.features.tracklist.recyclerview.TrackListRecyclerAdapter
import dmitriy_nazarov.ru.adplayer.features.tracklist.recyclerview.TrackListViewHolder
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track

class TrackListFragment : ViewModelRecyclerFragment<Track, TrackListRecyclerAdapter, TrackListViewModel>(), Observer<List<Track>> {

    override fun instanceViewModel(): Class<TrackListViewModel> {
        return TrackListViewModel::class.java
    }

    override fun instanceRecyclerAdapter(): TrackListRecyclerAdapter {
        return TrackListRecyclerAdapter(null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel!!.init(this)
        viewModel!!.getAllTrack(this)
    }

    override fun onChanged(tracks: List<Track>?) {
        addItems(tracks as ArrayList, false)
    }

}