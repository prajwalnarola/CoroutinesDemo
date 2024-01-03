package com.example.coroutinesdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinesdemo.databinding.DatalistBinding


class MainActivityAdapter(private var myDataItem: List<DataItems>) :
    RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder>(){
    inner class MainActivityViewHolder(binding: DatalistBinding ) : RecyclerView.ViewHolder(binding.root) {
        val tvId = binding.tvId
        val tvBody = binding.tvBody
    }

    fun setData(newItemList: List<DataItems>) {
        myDataItem = newItemList

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DatalistBinding.inflate(inflater, parent, false)
        return MainActivityViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myDataItem.size
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        val myData = myDataItem[position]
        holder.tvId.text = myData.id.toString()
        holder.tvBody.text = myData.body
    }
}