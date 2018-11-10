package dmitriy_nazarov.ru.adplayer.features.library

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.core.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        library_view_pager.adapter = LibraryFragmentsAdapter(context, childFragmentManager)
        tabs.setupWithViewPager(library_view_pager)

    }

    override fun getFragmentTag(): String = TAG

    companion object {

        const val TAG = "LibraryFragment"

    }

}