package dmitriy_nazarov.ru.adplayer.features.library

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.library.albumlist.AlbumsListFragment
import dmitriy_nazarov.ru.adplayer.features.library.tracklist.TrackListFragment

class LibraryFragmentsAdapter(var context: Context?, fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0){
            TrackListFragment()
        } else {
            AlbumsListFragment()
        }
    }

    override fun getCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0){
            context?.getString(R.string.tracks)
        } else {
            context?.getString(R.string.albums)
        }
    }

    companion object {

        const val FRAGMENT_COUNT = 2

    }

}