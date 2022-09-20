package com.pi.marvelapp.features.characterlist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pi.marvelapp.databinding.ItemCharacterListBinding
import com.pi.data.remote.response.Character
import com.pi.marvelapp.core.extensions.setOnDebouncedClickListener
import com.pi.marvelapp.core.platform.BaseListAdapter
import com.pi.marvelapp.core.platform.BaseViewHolder

/**
 * Adapter with viewModel to manage events
 * and DiffUtil to change the list without notifyData
 */
class CharacterAdapter() : BaseListAdapter<Character>(
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
     * Removing the exact item and submit the list with new one
     * Without using @notifyDataSetChanged()
     */
    fun removeItem(position: Int): Character {
        val characterList = ArrayList<Character>()
        currentList.forEach {
            characterList.add(it)
        }
        val removedItem = characterList.removeAt(position)
        submitList(characterList)

        return removedItem
    }

    /**
     * Binding Note model with its view - @DataBinding
     */
    internal inner class NoteViewHolder(parent: ViewGroup, inflater: LayoutInflater) :
        BaseViewHolder<ItemCharacterListBinding>(
            ItemCharacterListBinding.inflate(inflater, parent, false)
        ) {
        fun bind(character: Character) {
            binding.apply {
                item = character

                itemView.setOnDebouncedClickListener {
                    debouncedClickListener?.invoke(character)
                }

                executePendingBindings()
            }
        }
    }
}
