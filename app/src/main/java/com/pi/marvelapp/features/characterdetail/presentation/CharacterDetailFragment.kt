package com.pi.marvelapp.features.characterdetail.presentation

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.pi.marvelapp.R
import com.pi.marvelapp.core.binding.ViewBinding.visible
import com.pi.marvelapp.core.extensions.changeUiState
import com.pi.marvelapp.core.extensions.observe
import com.pi.marvelapp.core.navigation.PageName
import com.pi.marvelapp.core.platform.BaseFragment
import com.pi.marvelapp.core.utils.AppConstants.Companion.DEFAULT_DATE
import com.pi.marvelapp.core.utils.AppConstants.Companion.DEFAULT_ORDER_BY
import com.pi.marvelapp.core.utils.AppConstants.Companion.TEN_INT
import com.pi.marvelapp.core.utils.AppConstants.Companion.TWO_INT
import com.pi.marvelapp.core.utils.createDateRange
import com.pi.marvelapp.databinding.FragmentCharacterDetailBinding
import com.pi.marvelapp.features.characterdetail.domain.viewmodel.CharacterDetailViewModel
import com.pi.marvelapp.features.characterdetail.presentation.adapter.ComicAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A @Fragment for showing all Marvel Characters
 */
@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(
        layoutId = R.layout.fragment_character_detail,
        viewModelClass = CharacterDetailViewModel::class.java
    ) {
    /**
     * To know which page we are in - It might use in @DeepLink Feature
     */
    override fun getScreenKey(): String = PageName.PreLogin.CHARACTER_DETAILS_PAGE

    private lateinit var comicAdapter: ComicAdapter
    private val args: CharacterDetailFragmentArgs by navArgs()

    /**
     * Initialize view information
     */
    override fun setUpViews() {
        super.setUpViews()
        binding.apply {
            viewmodel = viewModel
            name = args.name
            description = args.description
            thumbnailUrl = args.thumbnailUrl

            // Setup Adapter with Recyclerview
            comicAdapter = ComicAdapter()
            comicRecyclerView.layoutManager = GridLayoutManager(requireContext(), TWO_INT)
            comicRecyclerView.adapter = comicAdapter
            comicRecyclerView.setHasFixedSize(false)
        }
    }

    /**
     * Get all Marvel Characters with limit and offset - Pagination
     */
    override fun getViewData() {
        viewModel.fetchCharacterComics(
            limit = TEN_INT,
            characterId = args.characterId,
            orderBy = DEFAULT_ORDER_BY,
            dateRange = createDateRange(DEFAULT_DATE)
        )
    }

    /**
     * Observing View Events and Adapter's list
     */
    override fun observeData() {

        observe(viewModel.uiStateFlow) { viewState ->
            binding.apply {
                progressBar.changeUiState(uiState = viewState.uiState)

                comicRecyclerView.visible = viewState.comicList.isNotEmpty()
                viewState.comicList.let { comicAdapter.submitList(it) }
            }
        }
    }
}
