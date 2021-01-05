package com.example.myplayer.ui.main.view

import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.myplayer.R
import com.example.myplayer.data.model.Id
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.fragment_video_play.*


class VideoPlayFragment : Fragment() {

    companion object {
        const val EXTRA_ITEM = "EXTRA_URL"
        fun newInstance(id: Id) = VideoPlayFragment().let { fragment ->
            Bundle().let {
                it.putParcelable(EXTRA_ITEM, id)
                fragment.arguments = it
            }
        }
    }

    private fun playVideo(videoUrl: String?) {

        var videoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        videoPlayer.playWhenReady = true
        player_view?.player = videoPlayer
        videoUrl?.let {
            buildMediaSource(it)?.let {
                videoPlayer?.prepare(it)

            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val a = arguments?.getParcelable<Id>(EXTRA_ITEM)
        val executor = object : YouTubeExtractor(requireContext()) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val itag = 18
                    val downloadUrl = ytFiles[itag].url
                    playVideo(downloadUrl)
                }
            }
        }
        executor.extract(a?.videoId, true, false)

        return inflater.inflate(R.layout.fragment_video_play, container, false)
    }

    private fun buildMediaSource(link: String): MediaSource? {
        val dataSourceFactory = DefaultDataSourceFactory(requireContext(), "sample")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(link))
    }
}