package com.example.toyexoplayer

import androidx.lifecycle.ViewModel
import io.reactivex.Single

/**
 * Created by hclee on 2019-08-07.
 */

class PlaylistViewModel : ViewModel() {
    fun getPlaylistSingle(): Single<List<PlaylistItem>> =
        PlaylistRepository.getPlaylistSingle()
}