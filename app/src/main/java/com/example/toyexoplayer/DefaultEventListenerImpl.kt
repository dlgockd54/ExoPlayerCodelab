package com.example.toyexoplayer

import android.util.Log
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player

/**
 * Created by hclee on 2019-08-06.
 */

class DefaultEventListenerImpl: Player.DefaultEventListener() {
    companion object {
        val TAG: String = DefaultEventListenerImpl::class.java.simpleName
    }
    
    override fun onLoadingChanged(isLoading: Boolean) {
        Log.d(TAG, "onLoadingChanged()")
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        Log.d(TAG, "onPlayerStateChanged()")

        when (playbackState) {
            Player.STATE_IDLE -> {
                Log.d(TAG, "state - IDLE")
            }
            Player.STATE_BUFFERING -> {
                Log.d(TAG, "state - BUFFERING")
            }
            Player.STATE_READY -> {
                Log.d(TAG, "state - READY")
            }
            Player.STATE_ENDED -> {
                Log.d(TAG, "state - ENDED")
            }
        }
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        Log.d(TAG, "onPlayerError()")

        when(error?.type) {
            ExoPlaybackException.TYPE_SOURCE -> {
                Log.d(TAG, "error - TYPE_SOURCE")
            }
            ExoPlaybackException.TYPE_RENDERER -> {
                Log.d(TAG, "error - TYPE_RENDERER")
            }
            ExoPlaybackException.TYPE_UNEXPECTED -> {
                Log.d(TAG, "error - TYPE_UNEXPECTED")
            }
        }
    }
}