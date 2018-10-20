package dmitriy_nazarov.ru.adplayer.features.start

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.db.LibraryTransform
import dmitriy_nazarov.ru.adplayer.features.db.MediaStoreAccessHelper
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumListRepository
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListRepository

private const val LIBRARY_NAV_DELAY_MS = 2000L

/**
 * A simple [Fragment] subclass.
 *
 */
class StartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.activity_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AlbumListRepository.insertAlbums(LibraryTransform.transformDeviceAlbumLibIntoAppLib(MediaStoreAccessHelper.getAllAlbums(context!!))!!)
        TrackListRepository.insertTracks(LibraryTransform.transformDeviceTrackLibIntoAppLib(MediaStoreAccessHelper.getAllTracks(context!!))!!)
        getView()?.postDelayed({Navigation.findNavController(activity!!, R.id.my_nav_host_fragment).navigate(R.id.libraryFragment)}, LIBRARY_NAV_DELAY_MS)
    }

}
