package com.pi.marvelapp.features.characterlist.presentation

import com.pi.marvelapp.R
import com.pi.marvelapp.core.binding.ViewBinding.visible
import com.pi.marvelapp.core.extensions.changeUiState
import com.pi.marvelapp.core.extensions.observe
import com.pi.marvelapp.core.navigation.PageName
import com.pi.marvelapp.core.platform.BaseFragment
import com.pi.marvelapp.databinding.FragmentCharacterListBinding
import com.pi.marvelapp.features.characterlist.domain.viewmodel.CharacterListViewModel
import com.pi.marvelapp.features.characterlist.presentation.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

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

    private lateinit var characterAdapter: CharacterAdapter

    /**
     * Initialize view informations and observing recyclerview gestures
     */
    override fun setUpViews() {
        super.setUpViews()
        binding.apply {
            viewmodel = viewModel

            // Setup Adapter with Recyclerview
            characterAdapter = CharacterAdapter()
            notesRecyclerView.setHasFixedSize(true)
            notesRecyclerView.adapter = characterAdapter

            /**
             * Adapter click listeners
             */
            characterAdapter.setOnDebouncedClickListener { note ->
                viewModel.navigateToCharacterDetails(character = note)
            }
        }
    }

    /**
     * Get all Notes from AppDb
     */
    override fun getViewData() {
        //viewModel.fetchAllCharacters()
    }

    /**
     * Observing View Events and noteList to fill the View
     */
    override fun observeData() {

        observe(viewModel.uiStateFlow) { viewState ->
            binding.apply {
                progressBar.changeUiState(uiState = viewState.uiState)

                notesRecyclerView.visible = viewState.characterList.isNotEmpty()

                characterAdapter.submitList(viewState.characterList)
            }
        }
    }
}
