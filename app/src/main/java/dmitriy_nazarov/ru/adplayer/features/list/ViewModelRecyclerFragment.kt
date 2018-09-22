package dmitriy_nazarov.ru.adplayer.features.list

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_recycler.*
import dmitriy_nazarov.ru.adplayer.R
import dmitriy_nazarov.ru.adplayer.features.livedata.BaseModel
import dmitriy_nazarov.ru.adplayer.features.livedata.BaseViewModelFragment


abstract class ViewModelRecyclerFragment<Model : BaseModel, Adapter : BaseRecyclerAdapter<Model, out BaseRecyclerViewHolder<Model>>, VM : ViewModel> : BaseViewModelFragment<VM>() {

    private lateinit var adapter: Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = getRecyclerLayoutManager()
        adapter = instanceRecyclerAdapter()
        recycler_view.adapter = adapter
        processShowEmptyListText()
    }

    open fun getRecyclerLayoutManager() : (RecyclerView.LayoutManager) = LinearLayoutManager(activity)

    abstract fun instanceRecyclerAdapter() : (Adapter)

    fun addItem(item: Model){
        adapter.addItem(item)
        adapter.notifyDataSetChanged()
        processShowEmptyListText()
    }

    fun addItems(items: MutableList<Model>, clearList: Boolean){
        adapter.addItems(items, clearList)
        adapter.notifyDataSetChanged()
        processShowEmptyListText()
    }

    fun getAdapter() : (Adapter?) = adapter

    private fun processShowEmptyListText(){
        if (adapter.itemCount > 0){
            empty_list_text_view.visibility =  View.GONE
        } else {
            empty_list_text_view.visibility = View.VISIBLE
        }
    }

}
