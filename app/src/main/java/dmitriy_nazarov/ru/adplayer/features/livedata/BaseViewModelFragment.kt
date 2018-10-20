package dmitriy_nazarov.ru.adplayer.features.livedata

import android.arch.lifecycle.*
import android.os.Bundle
import dmitriy_nazarov.ru.adplayer.ui.fragments.BaseFragment

abstract class BaseViewModelFragment<VM : ViewModel> : BaseFragment(), LifecycleOwner, ViewModelStoreOwner {

    var viewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(instanceViewModel())
    }

    abstract fun instanceViewModel(): Class<VM>

}