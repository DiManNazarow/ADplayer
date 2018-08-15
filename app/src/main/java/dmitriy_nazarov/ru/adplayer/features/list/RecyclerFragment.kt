package dmitriy_nazarov.ru.adplayer.features.list

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_recycler.*
import dmitriy_nazarov.ru.adplayer.R


abstract class RecyclerFragment<M : BaseRecyclerViewItem, VH : BaseRecyclerViewHolder<M>, A : BaseRecyclerAdapter<M, VH>> : Fragment() {

    private var adapter: A? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = getRecyclerLayoutManager()
        adapter = instanceRecyclerAdapter()
        recycler_view.adapter = adapter
        processShowEmptyListText()
    }

    open fun getRecyclerLayoutManager() : (RecyclerView.LayoutManager) = LinearLayoutManager(activity)

    abstract fun instanceRecyclerAdapter() : (A?)

    fun addItem(item: M){
        adapter?.addItem(item)
        adapter?.notifyDataSetChanged()
        processShowEmptyListText()
    }

    fun addItems(items: MutableList<M>, clearList: Boolean){
        adapter?.addItems(items, clearList)
        adapter?.notifyDataSetChanged()
        processShowEmptyListText()
    }

    fun getAdapter() : (A?) = adapter

    fun processShowEmptyListText(){
        if (adapter?.itemCount!! > 0){
            empty_list_text_view.visibility =  View.GONE
        } else {
            empty_list_text_view.visibility = View.VISIBLE
        }
    }

}
