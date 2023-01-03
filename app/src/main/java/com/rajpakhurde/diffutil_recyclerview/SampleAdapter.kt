package com.rajpakhurde.diffutil_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rajpakhurde.diffutil_recyclerview.databinding.ListItemBinding
import org.w3c.dom.Text

class SampleAdapter(
    val clickListener: ItemClickedListener
) : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    interface ItemClickedListener{
        fun onClicked(position: Int)
    }

    private var oldSampleList = mutableListOf<SampleModel>()

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

                cvItems.setOnClickListener {
                    clickListener.onClicked(position)
                }
            }
        }
    }

    fun setData(newSampleList: MutableList<SampleModel>){
        var diffUtil = MyDiffUtil(oldSampleList,newSampleList)
        var diffResult = DiffUtil.calculateDiff(diffUtil)
        oldSampleList = newSampleList
        diffResult.dispatchUpdatesTo(this)
    }

}