package com.example.toyexoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_playlist.*

class PlaylistActivity : AppCompatActivity() {
    companion object {
        val TAG: String = PlaylistActivity::class.java.simpleName
    }

    private val mPlaylistAdapter: PlaylistAdapter = PlaylistAdapter(this@PlaylistActivity, mutableListOf())
    private lateinit var mPlaylistViewModel: PlaylistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        rv_playlist.apply {
            adapter = mPlaylistAdapter
            layoutManager = LinearLayoutManager(this@PlaylistActivity)
        }

        mPlaylistViewModel = ViewModelProviders.of(this@PlaylistActivity).get(PlaylistViewModel::class.java)
            .apply {
                mPlayListLiveData.observe(this@PlaylistActivity, Observer {
                    with(mPlaylistAdapter) {
                        mPlayList.clear()
                        mPlayList.addAll(it)
                        notifyDataSetChanged()
                    }
                })
            }

        pullPlaylist()
    }

    private fun pullPlaylist() {
        mPlaylistViewModel.pullPlaylist()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")

        super.onDestroy()
    }
}
