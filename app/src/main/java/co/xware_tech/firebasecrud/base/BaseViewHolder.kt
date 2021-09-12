package co.xware_tech.firebasecrud.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView

const val ACTION_ITEM = "item"

abstract class BaseViewHolder<D : Any>(
    itemView: View,
    private val onClick: (String, D, Int) -> Unit = { _, _, _ -> }
) : RecyclerView.ViewHolder(itemView) {

    private var _data: D? = null

    val data: D
        get() = _data!!

    init {
        itemView.setOnClickListener {
            onClick(ACTION_ITEM, data, adapterPosition)
        }
    }

    fun setData(data: D) {
        _data = data
        bind(data)
    }
    open fun bindPlaceHolder(){

    }

    abstract fun bind(data: D)
}