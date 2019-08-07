package com.example.toyexoplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_playlist.view.*

/**
 * Created by hclee on 2019-08-07.
 */

class PlaylistAdapter(val mPlayList: MutableList<PlaylistItem>) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
        PlaylistViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false))

    override fun getItemCount(): Int = mPlayList.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        with(holder) {
            mTitleTextView.text = mPlayList[position].mTitle
            mDurationTextView.text = mPlayList[position].mDuration.toString()
        }
    }

    class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitleTextView: TextView = itemView.tv_playlist_title
        val mDurationTextView: TextView = itemView.tv_playlist_duration
    }
}