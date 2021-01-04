package com.example.myplayer.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayer.R
import com.example.myplayer.data.model.Item

class VideoListAdapter(private val items: List<Item>,private val listener: (Item)->Unit) :
    RecyclerView.Adapter<VideoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_item, parent, false)
        return VideoListViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: VideoListViewHolder, position: Int) =
        holder.bind(items[position], listener)

    override fun getItemCount(): Int {
        return items.size
    }

}