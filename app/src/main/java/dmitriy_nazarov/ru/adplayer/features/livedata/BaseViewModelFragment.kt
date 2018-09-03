package dmitriy_nazarov.ru.adplayer.features.livedata

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseViewModelFragment<VM : ViewModel> : Fragment(), LifecycleOwner, ViewModelStoreOwner {

    var viewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(instanceViewModel())
    }

    abstract fun instanceViewModel(): Class<VM>

}