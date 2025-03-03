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
import code.name.monkey.retromusic.model.GenreInfo
import org.koin.core.component.KoinComponent

object GenreUtil : KoinComponent {
    fun splitGenres(genres: List<Genre>): List<GenreInfo> {
        val splitters = PreferenceUtil.tagSeparators
            .filter { it.isChecked }
            .map { it.tagSeparator.separator }

        return genres
            .flatMap { createPairsFromGenre(splitters, it) }
            .groupBy({ it.first }) { it.second }
            .map {
                GenreInfo(
                    it.value.map { genre -> genre.id },
                    it.key,
                    it.value.sumOf { genre -> genre.songCount },
                )
            }
            .let { simplifyGenres(it) }
    }
}

private fun createPairsFromGenre(splitters: List<String>, genre: Genre): List<Pair<String, Genre>> {
    return genre.name
        .split(*splitters.toTypedArray())
        .map { it.trim() }
        .map { genreName -> Pair(genreName, genre) }
}

private fun simplifyGenres(genres: List<GenreInfo>): List<GenreInfo> {
    return genres
        .groupBy {
            it.name
                // e.g. Rhythm & Blues, Rhythm n Blues, Rhythm and Blues should be considered the same genre.
                .replace("&|and".toRegex(), "n")
                .lowercase()
                .replace("[^a-zA-Z0-9_]".toRegex(), "")
                // Remove all spaces.
                .replace("\\s+".toRegex(), "")
                // Special cases:
                .replace("rhythmnblues", "rnb")
                .replace("rap", "hip hop")
        }
        .map {
            GenreInfo(
                it.value.flatMap { genre -> genre.ids },
                // All genre names are similar on each group, so we can just pick the first element to represent
                // all the variants.
                it.value.first().name,
                it.value.sumOf { genre -> genre.songCount },
            )
        }
}
