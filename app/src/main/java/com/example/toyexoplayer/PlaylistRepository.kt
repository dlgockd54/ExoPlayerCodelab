package com.example.toyexoplayer

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by hclee on 2019-08-07.
 */

object PlaylistRepository {
    val testList: List<PlaylistItem> = listOf(
        PlaylistItem("abc", 111),
        PlaylistItem("def", 222),
        PlaylistItem("ghi", 333),
        PlaylistItem("abc", 111),
        PlaylistItem("def", 222),
        PlaylistItem("abc", 111),
        PlaylistItem("def", 222),
        PlaylistItem("ghi", 333),
        PlaylistItem("abc", 111),
        PlaylistItem("def", 222),
        PlaylistItem("ghi", 333)
    )

    fun getPlaylistSingle(): Single<List<PlaylistItem>> =
        Single.just(testList).subscribeOn(Schedulers.io())
}