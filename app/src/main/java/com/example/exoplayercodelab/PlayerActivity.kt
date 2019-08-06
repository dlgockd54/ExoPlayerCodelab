package com.example.exoplayercodelab

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.*
import kotlinx.android.synthetic.main.activity_player.*
import java.io.IOException
import java.lang.RuntimeException

class PlayerActivity : AppCompatActivity() {
    companion object {
        val TAG: String = PlayerActivity::class.java.simpleName
    }

    private var mPlayer: ExoPlayer? = null
    private var mPlayWhenReady: Boolean = false
    private var mPlaybackPosition: Long = 0L
    private var mCurrentWindowIndex: Int = 0
    private val mPlayerEventListener: DefaultEventListenerImpl = DefaultEventListenerImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
    }

    override fun onStart() {
        Log.d(TAG, "onStart()")

        super.onStart()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            initializePlayer()
        }
    }

    override fun onResume() {
        Log.d(TAG, "onResume()")

        super.onResume()

        hideSystemUi()

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M || mPlayer == null) {
            initializePlayer()
        }
    }

    private fun initializePlayer() {
        Log.d(TAG, "initializePlayer()")

        mPlayer = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(this@PlayerActivity),
            DefaultTrackSelector(),
            DefaultLoadControl()
        ).apply {
            addListener(mPlayerEventListener)
            playWhenReady = mPlayWhenReady
            seekTo(mCurrentWindowIndex, mPlaybackPosition)
            prepare(buildMediaSource(Uri.parse(getString(R.string.sample_url_mp4))),
                false,
                false)
        }

        pv_video.player = mPlayer
    }

    private fun releasePlayer() {
        Log.d(TAG, "releasePlayer()")

        mPlayer?.let {
            mPlaybackPosition = it.currentPosition
            mPlayWhenReady = it.playWhenReady
            mCurrentWindowIndex = it.currentWindowIndex
            it.removeListener(mPlayerEventListener)
            it.release()
        }

        mPlayer = null
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(
                DefaultHttpDataSourceFactory("exoplayer-codelab", object : TransferListener<DataSource> {
                    override fun onTransferStart(source: DataSource?, dataSpec: DataSpec?) {
                        Log.d(TAG, "onTransferStart()")
                    }

                    override fun onTransferEnd(source: DataSource?) {
                        Log.d(TAG, "onTransferEnd()")
                    }

                    override fun onBytesTransferred(source: DataSource?, bytesTransferred: Int) {
                        Log.d(TAG, "onByteTransferred()")
                    }
                })
        ).createMediaSource(uri)
    }

    private fun hideSystemUi() {
        with(pv_video) {
            systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }

    override fun onPause() {
        Log.d(TAG, "onPause()")

        super.onPause()

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            releasePlayer()
        }
    }

    override fun onStop() {
        Log.d(TAG, "onStop()")

        super.onStop()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            releasePlayer()
        }
    }
}
