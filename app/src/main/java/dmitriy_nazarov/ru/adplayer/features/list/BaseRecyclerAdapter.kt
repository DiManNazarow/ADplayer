package dmitriy_nazarov.ru.adplayer.features.list

import android.support.v7.widget.RecyclerView
import dmitriy_nazarov.ru.adplayer.features.livedata.BaseModel

/**
 * Created by Dmitry Nazarov on 16-Aug-18.
 */
abstract class BaseRecyclerAdapter<M : BaseModel, VH : BaseRecyclerViewHolder<M>>(private var items: MutableList<M>?) : RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int {
        if (items == null || items!!.isEmpty()){
            return 0
        } else {
            return items!!.size
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.fill(items!!.get(position))
    }

    open fun addItem(item: M){
        initList()
        items!!.add(item)
    }

    open fun addItems(items: MutableList<M>, clearList: Boolean){
        initList()
        when (clearList) {
            true -> {
                this.items!!.clear()
                this.items!!.addAll(items)
            }
            false -> this.items!!.addAll(items)
        }
    }

    fun getItems(): (MutableList<M>?) = items

    private fun initList(){
        if (items == null){
            items = mutableListOf()
        }
    }

}