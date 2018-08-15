package dmitriy_nazarov.ru.adplayer.features.list

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Dmitry Nazarov on 16-Aug-18.
 */
abstract class BaseRecyclerViewHolder<M : BaseRecyclerViewItem> : RecyclerView.ViewHolder {

    protected var model: M? = null

    constructor(itemView: View) : this(itemView, null) {

    }

    constructor(itemView: View, model: M?) : super(itemView) {
        this.model = model
    }

    public fun fill(model: M?){
        this.model = model
        fill()
    }

    abstract fun fill()

}