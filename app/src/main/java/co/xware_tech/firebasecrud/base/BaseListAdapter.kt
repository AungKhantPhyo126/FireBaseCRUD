package co.xware_tech.firebasecrud.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

typealias OnClick<D> = (String, D, Int) -> Unit

abstract class BaseListAdapter<D : Any, VH : BaseViewHolder<D>>(
    diffCallback: DiffUtil.ItemCallback<D>,
    val onClick: OnClick<D> = { _, _ ,_ -> }
) : ListAdapter<D, VH>(diffCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setData(getItem(position))
    }

    abstract fun getItemLayoutId(): Int

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
}