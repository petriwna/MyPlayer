package com.example.myplayer.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class VideoListDao{

    @Query("Select * From VideoListResults")
    abstract fun getVideoList():LiveData<List<VideoListResults>>

    @Query("Select* From VideoListResults where videoId = :videoId")
    abstract fun getVideoById(videoId: Long) : LiveData<List<VideoListResults>>
}