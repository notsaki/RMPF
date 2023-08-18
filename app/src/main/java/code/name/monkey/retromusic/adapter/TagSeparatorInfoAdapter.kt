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
package code.name.monkey.retromusic.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import code.name.monkey.appthemehelper.ThemeStore.Companion.accentColor
import code.name.monkey.retromusic.databinding.PreferenceDialogTagSeparatorsListitemBinding
import code.name.monkey.retromusic.model.TagSeparatorInfo
import code.name.monkey.retromusic.util.PreferenceUtil

class TagSeparatorInfoAdapter : RecyclerView.Adapter<TagSeparatorInfoAdapter.ViewHolder>() {
    var tagSeparatorInfos: MutableList<TagSeparatorInfo> =
        PreferenceUtil.tagSeparators.toMutableList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return tagSeparatorInfos.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tagSeparator = tagSeparatorInfos[position]
        holder.binding.checkbox.isChecked = tagSeparator.isChecked
        holder.binding.title.text =
            holder.binding.title.resources.getString(tagSeparator.tagSeparator.stringRes)
        holder.itemView.setOnClickListener {
            tagSeparator.isChecked = !tagSeparator.isChecked
            holder.binding.checkbox.isChecked = tagSeparator.isChecked
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder(
            PreferenceDialogTagSeparatorsListitemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    class ViewHolder(val binding: PreferenceDialogTagSeparatorsListitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.checkbox.buttonTintList =
                ColorStateList.valueOf(accentColor(binding.checkbox.context))
        }
    }
}
