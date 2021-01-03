package com.example.myplayer.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myplayer.data.model.Item
import kotlinx.android.synthetic.main.video_item.view.*

class VideoListViewHolder(v: View): RecyclerView.ViewHolder(v){

    fun bind(item: Item){
        with(itemView){
            tv_name_video.text = item.snippet.title
            Glide.with(context).load(item.snippet.thumbnails.high.url).into(iv_video_img)
        }
    }

}