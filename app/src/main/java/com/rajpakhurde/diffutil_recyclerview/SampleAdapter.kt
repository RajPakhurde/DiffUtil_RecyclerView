package com.rajpakhurde.diffutil_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rajpakhurde.diffutil_recyclerview.databinding.ListItemBinding

class SampleAdapter : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    private var oldSampleList = emptyList<SampleModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = oldSampleList.size

    inner class SampleViewHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                tvId.text = oldSampleList[position].id.toString()
                tvName.text = oldSampleList[position].name
            }
        }
    }

    fun setData(newSampleList: List<SampleModel>){
        var diffUtil = MyDiffUtil(oldSampleList,newSampleList)
        var diffResult = DiffUtil.calculateDiff(diffUtil)
        oldSampleList = newSampleList
        diffResult.dispatchUpdatesTo(this)
    }

}