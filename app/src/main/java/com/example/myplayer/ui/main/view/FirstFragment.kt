package com.example.myplayer.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.myplayer.R
import com.example.myplayer.data.api.ApiInterface
import com.example.myplayer.data.model.Item
import com.example.myplayer.ui.main.adapter.VideoListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val list = ApiInterface.create().getVideoResult().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    rv_video_list.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = it.body()?.items?.let { it1 -> VideoListAdapter(it1,::openFragment) }
                    }

                }, {
                    it.printStackTrace()
                }
            )
    }

    fun openFragment(item: Item){
        findNavController().navigate(R.id.action_firstFragment_to_videoPlayFragment)
    }

}
