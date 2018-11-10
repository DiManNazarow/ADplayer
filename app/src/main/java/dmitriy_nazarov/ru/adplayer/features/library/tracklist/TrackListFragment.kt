package dmitriy_nazarov.ru.adplayer.features.library.tracklist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.models.Track
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.recyclerview.TrackListRecyclerAdapter
import dmitriy_nazarov.ru.adplayer.features.core.list.ViewModelRecyclerFragment
import javax.inject.Inject

class TrackListFragment : ViewModelRecyclerFragment<Track, TrackListRecyclerAdapter, TrackListViewModel>(), Observer<List<Track>> {

    @Inject
    lateinit var trackListRepository: TrackListRepository

    override fun instanceViewModel(): Class<TrackListViewModel> {
        return TrackListViewModel::class.java
    }

    override fun instanceRecyclerAdapter(): TrackListRecyclerAdapter {
        return TrackListRecyclerAdapter(null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.init(this, trackListRepository)
        viewModel?.getAllTrack(this)
    }

    override fun onChanged(tracks: List<Track>?) {
        addItems(tracks as ArrayList, false)
    }

    override fun getFragmentTag(): String = TAG

    companion object {

        const val TAG = "TrackListFragment"

    }

}