package dmitriy_nazarov.ru.adplayer.features.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmitriy_nazarov.ru.adplayer.ADPlayerApp
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.RetryWithDelay
import dmitriy_nazarov.ru.adplayer.features.core.db.LibraryTransform
import dmitriy_nazarov.ru.adplayer.features.core.db.MediaStoreAccessHelper
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumListRepository
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.TrackListRepository
import dmitriy_nazarov.ru.adplayer.ui.fragments.BaseFragment
import dmitriy_nazarov.ru.adplayer.utils.TestDataManager
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_start.*
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StartFragment : BaseFragment() {

    @Inject
    lateinit var trackListRepository: TrackListRepository
    @Inject
    lateinit var albumListRepository: AlbumListRepository

    lateinit var progressDisposable: Disposable

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

        getView()?.post { createAndStartProgressObeservation() }

    }

    private fun createAndStartProgressObeservation() {
        progressDisposable = Observable.
                interval(20, TimeUnit.MILLISECONDS).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe { int -> updateProgress(int.toInt()) }
    }

    private fun updateProgress(progress: Int) {
        if (progress >= 100) {
            progressDisposable.dispose()
            getNavigationController().navigate(R.id.libraryFragment)
        }
        progress_bar.progress = progress
    }

}
