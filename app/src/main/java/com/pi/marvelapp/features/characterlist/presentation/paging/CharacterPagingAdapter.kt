package com.pi.marvelapp.features.characterlist.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pi.data.remote.response.CharacterListResponse.Data.CharacterInfo
import com.pi.marvelapp.core.extensions.setOnDebouncedClickListener
import com.pi.marvelapp.core.platform.BasePagingAdapter
import com.pi.marvelapp.core.platform.BaseViewHolder
import com.pi.marvelapp.databinding.ItemCharacterListBinding

class CharacterPagingAdapter() : BasePagingAdapter<CharacterInfo>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return CharacterViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterPagingAdapter.CharacterViewHolder -> {
                getItem(position)?.let { holder.bind(it) }
            }
        }
    }

    /**
     * Binding CharacterInfo model with its view - @DataBinding
     */
    internal inner class CharacterViewHolder(parent: ViewGroup, inflater: LayoutInflater) :
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