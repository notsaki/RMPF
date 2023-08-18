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
package code.name.monkey.retromusic.model

import android.os.Parcelable
import androidx.annotation.StringRes
import code.name.monkey.retromusic.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class TagSeparatorInfo(
    val tagSeparator: TagSeparator,
    var isChecked: Boolean,
) : Parcelable {
    enum class TagSeparator(
        @StringRes val stringRes: Int,
        val separator: String,
    ) {
        Semicolon(R.string.pref_tag_separator_semicolon, ";"),
        Slash(R.string.pref_tag_separator_slash, "/"),
        Comma(R.string.pref_tag_separator_comma, ","),
        Plus(R.string.pref_tag_separator_plus, "+"),
        Ampersand(R.string.pref_tag_separator_ampersand, "&"),
    }
}
