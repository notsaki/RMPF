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

package code.name.monkey.retromusic.preferences

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat.SRC_IN
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import code.name.monkey.appthemehelper.common.prefs.supportv7.ATEDialogPreference
import code.name.monkey.retromusic.R
import code.name.monkey.retromusic.adapter.TagSeparatorInfoAdapter
import code.name.monkey.retromusic.databinding.PreferenceDialogTagSeparatorsBinding
import code.name.monkey.retromusic.extensions.colorButtons
import code.name.monkey.retromusic.extensions.colorControlNormal
import code.name.monkey.retromusic.extensions.materialDialog
import code.name.monkey.retromusic.model.TagSeparatorInfo
import code.name.monkey.retromusic.util.PreferenceUtil


class TagSeparatorsPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ATEDialogPreference(context, attrs, defStyleAttr, defStyleRes) {
    init {
        icon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
            context.colorControlNormal(),
            SRC_IN
        )
    }
}

class TagSeparatorsPreferenceDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = PreferenceDialogTagSeparatorsBinding.inflate(layoutInflater)

        val tagSeparatorAdapter = TagSeparatorInfoAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tagSeparatorAdapter
        }

        return materialDialog(R.string.pref_tag_separators_title)
            .setNeutralButton(
                R.string.reset_action
            ) { _, _ ->
                updateTagSeparators(PreferenceUtil.defaultTagSeparators)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .setPositiveButton(R.string.done) { _, _ -> updateTagSeparators(tagSeparatorAdapter.tagSeparatorInfos) }
            .setView(binding.root)
            .create()
            .colorButtons()
    }

    private fun updateTagSeparators(tagSeparators: List<TagSeparatorInfo>) {
        PreferenceUtil.tagSeparators = tagSeparators
    }

    companion object {
        fun newInstance(): TagSeparatorsPreferenceDialog {
            return TagSeparatorsPreferenceDialog()
        }
    }
}
