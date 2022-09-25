package com.pi.marvelapp.features.characterdetail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pi.data.remote.response.CharacterComicsResponse
import com.pi.marvelapp.core.extensions.setOnDebouncedClickListener
import com.pi.marvelapp.core.platform.BaseListAdapter
import com.pi.marvelapp.core.platform.BaseViewHolder
import com.pi.marvelapp.databinding.ItemComicListBinding

/**
 * Adapter with viewModel to manage events
 * and DiffUtil to change the list without notifyData
 */
class ComicAdapter : BaseListAdapter<CharacterComicsResponse.Data.Result>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ComicViewHolder(parent, inflater)
    }

    /**
     * Binding Comic Item to its view
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ComicViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    /**
     * Binding Comic model with its view - @DataBinding
     */
    internal inner class ComicViewHolder(parent: ViewGroup, inflater: LayoutInflater) :
        BaseViewHolder<ItemComicListBinding>(
            ItemComicListBinding.inflate(inflater, parent, false)
        ) {
        fun bind(comicInfo: CharacterComicsResponse.Data.Result) {
            binding.apply {
                item = comicInfo

                itemView.setOnDebouncedClickListener {
                    debouncedClickListener?.invoke(comicInfo)
                }

                executePendingBindings()
            }
        }
    }
}
