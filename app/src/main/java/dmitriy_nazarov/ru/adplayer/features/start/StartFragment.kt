package dmitriy_nazarov.ru.adplayer.features.start

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dmitriy_nazarov.ru.adplayer.ADPlayerApp
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.db.LibraryTransform
import dmitriy_nazarov.ru.adplayer.features.db.MediaStoreAccessHelper
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumListRepository
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.TrackListRepository
import dmitriy_nazarov.ru.adplayer.ui.fragments.BaseFragment
import dmitriy_nazarov.ru.adplayer.utils.TestDataManager
import javax.inject.Inject

private const val LIBRARY_NAV_DELAY_MS = 2000L

class StartFragment : BaseFragment() {

    @Inject
    lateinit var trackListRepository: TrackListRepository
    @Inject
    lateinit var albumListRepository: AlbumListRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.activity_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ADPlayerApp.isTestMode()) {
            albumListRepository.insertAlbums(TestDataManager.albumsList)
            trackListRepository.insertTracks(TestDataManager.tracksList)
        } else {
            albumListRepository.insertAlbums(LibraryTransform.transformDeviceAlbumLibIntoAppLib(MediaStoreAccessHelper.getAllAlbums(context!!))!!)
            trackListRepository.insertTracks(LibraryTransform.transformDeviceTrackLibIntoAppLib(MediaStoreAccessHelper.getAllTracks(context!!))!!)
        }
        getView()?.postDelayed({ getNavigationController().navigate(R.id.libraryFragment)}, LIBRARY_NAV_DELAY_MS)
    }

}
