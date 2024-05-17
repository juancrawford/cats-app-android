package com.catsapp.android.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.catsapp.android.domain.BreedUseCase
import com.catsapp.android.paging.CatsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * ViewModel for managing the main screen's data.
 *
 * This ViewModel class is responsible for providing data to the main screen. It uses a
 * Pager to load paginated data from a data source and maps the loaded data into
 * CatItemViewModel instances for display in the UI.
 *
 * @param breedUseCase The use case for fetching cat data.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val breedUseCase: BreedUseCase) : ViewModel() {

    /**
     * Flow of CatItemViewModel instances representing the list of cats.
     */
    val catsList = Pager(PagingConfig(1)) {
        CatsPagingSource(breedUseCase)
    }.flow.map { pagingData -> pagingData.map { cat -> CatItemViewModel(cat) }
    }.cachedIn(viewModelScope)
}