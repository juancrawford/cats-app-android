package com.catsapp.android.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catsapp.android.model.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for managing data related to the detail screen.
 *
 * This ViewModel class is responsible for managing data related to the detail screen,
 * such as the title and description of a cat. It provides methods to load data into
 * LiveData objects based on the provided Cat instance.
 */
@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    /**
     * LiveData representing the title of the cat.
     */
    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    /**
     * LiveData representing the description of the cat.
     */
    private val _description = MutableLiveData("")
    val description: LiveData<String> = _description

    /**
     * Loads data into the LiveData objects based on the provided Cat instance.
     *
     * @param cat The Cat instance containing data to be loaded.
     */
    fun loadData(cat: Cat) {
        _title.postValue(cat.breeds.firstOrNull()?.name.orEmpty())
        _description.postValue(cat.breeds.firstOrNull()?.description.orEmpty())
    }
}