/*
 * Copyright (c) 2020 Hemanth Savarla.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 */
package code.name.monkey.retromusic.fragments.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import code.name.monkey.retromusic.interfaces.IMusicServiceEventListener
import code.name.monkey.retromusic.model.GenreInfo
import code.name.monkey.retromusic.model.Song
import code.name.monkey.retromusic.repository.RealRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenreDetailsViewModel(
    private val realRepository: RealRepository,
    private val genre: GenreInfo
) : ViewModel(), IMusicServiceEventListener {

    private val _playListSongs = MutableLiveData<List<Song>>()

    fun getSongs(): LiveData<List<Song>> = _playListSongs

    init {
        loadGenreSongs(genre)
    }

    private fun loadGenreSongs(genre: GenreInfo) = viewModelScope.launch(IO) {
        val songs = realRepository.getGenres(genre.ids)
        withContext(Main) { _playListSongs.postValue(songs) }
    }

    override fun onMediaStoreChanged() {
        loadGenreSongs(genre)
    }

    override fun onServiceConnected() {}
    override fun onServiceDisconnected() {}
    override fun onQueueChanged() {}
    override fun onPlayingMetaChanged() {}
    override fun onPlayStateChanged() {}
    override fun onRepeatModeChanged() {}
    override fun onShuffleModeChanged() {}
    override fun onFavoriteStateChanged() {}
}
