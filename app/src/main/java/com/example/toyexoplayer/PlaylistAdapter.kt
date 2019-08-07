package com.example.toyexoplayer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_playlist.view.*

/**
 * Created by hclee on 2019-08-07.
 */

class PlaylistAdapter(private val mActivity: Activity, val mPlayList: MutableList<PlaylistItem>) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    companion object {
        val TAG: String = PlaylistAdapter::class.java.simpleName
        val URI_KEY: String = "contents_uri"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
        PlaylistViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false))

    override fun getItemCount(): Int = mPlayList.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        with(holder) {
            mTitleTextView.text = mPlayList[position].mTitle
            mDurationTextView.text = mPlayList[position].mDuration.toString()

            itemView.setOnClickListener {
                with(mActivity) {
                    startActivity(Intent(this, PlayerActivity::class.java).apply {
                        putExtra(URI_KEY, mPlayList[position].mUriString)
                    })
                    overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left)
                }
            }
        }
    }

    class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitleTextView: TextView = itemView.tv_playlist_title
        val mDurationTextView: TextView = itemView.tv_playlist_duration
    }
}