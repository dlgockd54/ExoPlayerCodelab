package com.example.toyexoplayer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by hclee on 2019-08-07.
 */

class PlaylistViewModel : ViewModel() {
    private val mCompositeDisposable = CompositeDisposable()

    val mPlayListLiveData: MutableLiveData<List<PlaylistItem>> =
        MutableLiveData<List<PlaylistItem>>()

    fun pullPlaylist() {
        mCompositeDisposable.add(
            PlaylistRepository.getPlaylistSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mPlayListLiveData.postValue(it)
                }, {

                })
        )
    }

    override fun onCleared() {
        mCompositeDisposable.clear()

        super.onCleared()
    }
}