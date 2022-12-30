package com.rajpakhurde.diffutil_recyclerview

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil(
    private var oldList: List<SampleModel>,
    private var newList: List<SampleModel>

): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
         return oldList.size
    }

    override fun getNewListSize(): Int {
         return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
         return when{
             oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                 false
             }
             oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                 false
             }
             else -> true
         }
    }


}