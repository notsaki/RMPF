/*
 * Copyright (c) 2019 Hemanth Savarala.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by
 *  the Free Software Foundation either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */
package code.name.monkey.retromusic.util

import code.name.monkey.retromusic.model.Genre
import code.name.monkey.retromusic.model.GenreSplit
import org.koin.core.component.KoinComponent

object GenreUtil : KoinComponent {
    private val splitters = listOf(";", "/", ",")
    const val genreIdSplitter = ";"

    fun splitGenres(genres: List<Genre>): List<GenreSplit> {
        return genres
            .flatMap { createPairsFromGenre(it) }
            .groupBy({ it.first }) { it.second }
            .map {
                GenreSplit(
                    it.value.map { genre -> genre.id }.joinToString(";"),
                    it.key,
                    it.value.sumOf { genre -> genre.songCount },
                )
            }
    }

    private fun createPairsFromGenre(genre: Genre): List<Pair<String, Genre>> {
        return genre.name
            .split(*splitters.toTypedArray())
            .map { genreName -> Pair(genreName, genre) }
    }

}
