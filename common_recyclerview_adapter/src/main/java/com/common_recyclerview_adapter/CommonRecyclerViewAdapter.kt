package com.common_recyclerview_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class CommonRecyclerViewAdapter<T,V:ViewDataBinding>(val context: Context,@LayoutRes val layoutId:Int): RecyclerView.Adapter<CommonRecyclerViewAdapter.BaseViewHolder<V>>() {
 
    private val itemList: MutableList<T> = mutableListOf()

    override fun getItemCount(): Int=itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        BaseViewHolder<V>(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BaseViewHolder<V>,position: Int) {
        getViewData(holder.binding,position,itemList)
      //getViewData(holder.binding,position,itemList[position])
    }

    abstract fun getViewData(itemView: V, position: Int, itemList: MutableList<T>)
  //abstract fun getViewData(rowBinding: V, position: Int, item: T)

    open class BaseViewHolder<V: ViewDataBinding>(val binding:V) : RecyclerView.ViewHolder(binding.root)


    //---------------------------------- utility methods----------------------------------------//
    fun getItem(position: Int): T? {
        return if (position > itemList.size) {
            null
        } else itemList[position]
    }

    fun addData(itemModel: List<T>?) {
        itemList.addAll(itemModel!!)
        notifyDataSetChanged()
    }

    fun updateDataAtPosition(position: Int, data: T) {
        itemList[position] = data
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clearData() {
        itemList.clear()
        notifyDataSetChanged()
    }

    fun clearAndAddData(itemModel: List<T>?) {
        itemList.clear()
        itemList.addAll(itemModel!!)
        notifyDataSetChanged()
    }

    fun updateDataAtIndex(itemModel: T, index: Int) {
        itemList[index] = itemModel
        this.notifyItemChanged(index)
    }

    fun removeItemFromIndex(index: Int) {
        itemList.removeAt(index)
        notifyDataSetChanged()
    }

    fun addItemAtLast(itemModel: T) {
        itemList.add(itemModel)
        notifyItemInserted(itemList.size - 1)
    }

    fun addItemAtIndex(itemModel: T, index: Int) {
        itemList.add(index, itemModel)
        notifyItemInserted(index)
    }

}