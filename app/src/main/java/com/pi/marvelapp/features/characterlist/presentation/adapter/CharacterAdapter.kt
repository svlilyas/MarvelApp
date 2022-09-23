package com.pi.marvelapp.features.characterlist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pi.data.remote.response.CharacterListResponse.Data.CharacterInfo
import com.pi.marvelapp.core.extensions.setOnDebouncedClickListener
import com.pi.marvelapp.core.platform.BaseListAdapter
import com.pi.marvelapp.core.platform.BaseViewHolder
import com.pi.marvelapp.databinding.ItemCharacterListBinding

/**
 * Adapter with viewModel to manage events
 * and DiffUtil to change the list without notifyData
 */
class CharacterAdapter() : BaseListAdapter<CharacterInfo>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return NoteViewHolder(parent, inflater)
    }

    /**
     * Binding Note Item to its view
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NoteViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    /**
     * Binding Note model with its view - @DataBinding
     */
    internal inner class NoteViewHolder(parent: ViewGroup, inflater: LayoutInflater) :
        BaseViewHolder<ItemCharacterListBinding>(
            ItemCharacterListBinding.inflate(inflater, parent, false)
        ) {
        fun bind(characterInfo: CharacterInfo) {
            binding.apply {
                item = characterInfo

                itemView.setOnDebouncedClickListener {
                    debouncedClickListener?.invoke(characterInfo)
                }

                executePendingBindings()
            }
        }
    }
}
