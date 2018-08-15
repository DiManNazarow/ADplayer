package dmitriy_nazarov.ru.adplayer.features.list

import android.support.v7.widget.RecyclerView

/**
 * Created by Dmitry Nazarov on 16-Aug-18.
 */
abstract class BaseRecyclerAdapter<M : BaseRecyclerViewItem, VH : BaseRecyclerViewHolder<M>> : RecyclerView.Adapter<VH> {

    private var items: MutableList<M> = mutableListOf()

    constructor(items: MutableList<M>) : super() {
        this.items = items;
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.fill(items.get(position))
    }

    open fun addItem(item: M){
        items.add(item)
    }

    open fun addItems(items: MutableList<M>, clearList: Boolean){
        when (clearList) {
            true -> {
                items.clear()
                items.addAll(items)
            }
            false -> items.addAll(items)
        }
    }

    fun getItems() : (MutableList<M>) = items

}