package dmitriy_nazarov.ru.adplayer.ui.fragments

import android.content.Context
import android.support.v4.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.android.support.AndroidSupportInjection
import dmitriy_nazarov.ru.adplayer.R

abstract class BaseFragment: Fragment()  {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    protected fun getNavigationController(): NavController = Navigation.findNavController(activity!!, getNavigationControllerId())

    protected fun getNavigationControllerId(): Int = R.id.main_activity_navigation_graph

}