package com.pi.marvelapp.features.characterlist.presentation

import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.pi.marvelapp.R
import com.pi.marvelapp.core.extensions.changeUiState
import com.pi.marvelapp.core.extensions.observe
import com.pi.marvelapp.core.navigation.PageName
import com.pi.marvelapp.core.platform.BaseFragment
import com.pi.marvelapp.core.utils.AppConstants.Companion.THIRTY_INT
import com.pi.marvelapp.core.utils.AppConstants.Companion.TWO_INT
import com.pi.marvelapp.databinding.FragmentCharacterListBinding
import com.pi.marvelapp.features.characterlist.domain.viewmodel.CharacterListViewModel
import com.pi.marvelapp.features.characterlist.presentation.paging.CharacterPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A @Fragment for showing all Marvel Characters
 */
@AndroidEntryPoint
class CharacterListFragment :
    BaseFragment<FragmentCharacterListBinding, CharacterListViewModel>(
        layoutId = R.layout.fragment_character_list,
        viewModelClass = CharacterListViewModel::class.java
    ) {
    /**
     * To know which page we are in - It might use in @DeepLink Feature
     */
    override fun getScreenKey(): String = PageName.PreLogin.CHARACTER_LIST_PAGE

    private lateinit var characterAdapter: CharacterPagingAdapter

    /**
     * Initialize view information
     */
    override fun setUpViews() {
        super.setUpViews()
        binding.apply {
            viewmodel = viewModel

            // Setup Adapter with Recyclerview
            characterAdapter = CharacterPagingAdapter()
            characterRecyclerView.setHasFixedSize(true)
            characterRecyclerView.layoutManager = GridLayoutManager(requireContext(), TWO_INT)
            characterRecyclerView.adapter = characterAdapter

            /**
             * Adapter click listener
             */
            characterAdapter.setOnDebouncedClickListener { characterInfo ->
                viewModel.navigateToCharacterDetails(characterInfo = characterInfo)
            }
        }
    }

    /**
     * Get all Marvel Characters with limit and offset - Pagination
     */
    override fun getViewData() {
        viewModel.fetchAllCharacters(limit = THIRTY_INT)
    }

    /**
     * Observing View Events and Adapter's list
     */
    override fun observeData() {

        observe(viewModel.uiStateFlow) { viewState ->
            binding.apply {
                progressBar.changeUiState(uiState = viewState.uiState)

                viewmodel?.viewModelScope?.launch {
                    viewState.characterList?.let { characterAdapter.submitData(it) }
                }
            }
        }
    }
}
